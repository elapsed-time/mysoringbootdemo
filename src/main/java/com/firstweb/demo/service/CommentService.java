package com.firstweb.demo.service;

import com.firstweb.demo.enums.CommentTypeEnum;
import com.firstweb.demo.exception.CustomizeErrorCode;
import com.firstweb.demo.exception.CustomizeException;
import com.firstweb.demo.mapper.CommentMapper;
import com.firstweb.demo.mapper.QuestionExtMapper;
import com.firstweb.demo.mapper.QuestionMapper;
import com.firstweb.demo.mapper.UserMapper;
import com.firstweb.demo.model.*;
import com.firstweb.demo.pojo.CommentPOJO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zxx
 * @2019/7/26 17:54
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Transactional//事务注解，让commentMapper.insert(comment);questionExtMapper.incCommentCount(question);同时发生，一个失败则全部回滚
    public void insert(Comment comment) {
        if (comment.getParentId()==null||comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType()==null|| !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbcomment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbcomment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUNT);
            }
            commentMapper.insert(comment);
        }else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUNT);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }

    public List<CommentPOJO> ListByTarget(long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (comments.size()==0){
            return new ArrayList<>();
        }
        //java 8语法stream.map
        //获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds=new ArrayList();
        userIds.addAll(commentators);

        //获取评论人并转换为Map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long,User> userMap=users.stream().collect(Collectors.toMap(user->user.getId(),user->user));

        //转换comment为commentPOJO
        List<CommentPOJO> commentPOJOS = comments.stream().map(comment -> {
            CommentPOJO commentPOJO = new CommentPOJO();
            BeanUtils.copyProperties(comment, commentPOJO);
            commentPOJO.setUser(userMap.get(comment.getCommentator()));
            return commentPOJO;
        }).collect(Collectors.toList());
        return commentPOJOS;
    }
}
