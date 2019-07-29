package com.firstweb.demo.controller;

import com.firstweb.demo.enums.CommentTypeEnum;
import com.firstweb.demo.exception.CustomizeErrorCode;
import com.firstweb.demo.model.Comment;
import com.firstweb.demo.model.User;
import com.firstweb.demo.pojo.CommentCreatePOJO;
import com.firstweb.demo.pojo.CommentPOJO;
import com.firstweb.demo.pojo.ResultCodePOJO;
import com.firstweb.demo.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zxx
 * @2019/7/26 15:36
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreatePOJO commentCreatePOJO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultCodePOJO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if (commentCreatePOJO == null || StringUtils.isBlank(commentCreatePOJO.getContent())) {
            return ResultCodePOJO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreatePOJO.getParentId());
        comment.setContent(commentCreatePOJO.getContent());
        comment.setType(commentCreatePOJO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount((long) 0);
        commentService.insert(comment);
        return ResultCodePOJO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultCodePOJO comments(@PathVariable(name = "id") Long id) {
        List<CommentPOJO> commentPOJOS = commentService.ListByTarget(id, CommentTypeEnum.COMMENT);
        return ResultCodePOJO.okOf(commentPOJOS);
    }
}
