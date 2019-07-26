package com.firstweb.demo.controller;

import com.firstweb.demo.mapper.CommentMapper;
import com.firstweb.demo.model.Comment;
import com.firstweb.demo.pojo.CommentPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zxx
 * @2019/7/26 15:36
 */
@Controller
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentPOJO commentPOJO){
        Comment comment = new Comment();
        comment.setParentId(commentPOJO.getParentId());
        comment.setContent(commentPOJO.getContent());
        comment.setType(commentPOJO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
        comment.setLikeCount((long) 0);
        commentMapper.insert(comment);
        return null;
    }
}
