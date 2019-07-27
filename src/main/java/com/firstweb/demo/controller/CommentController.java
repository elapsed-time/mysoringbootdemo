package com.firstweb.demo.controller;

import com.firstweb.demo.exception.CustomizeErrorCode;
import com.firstweb.demo.model.Comment;
import com.firstweb.demo.model.User;
import com.firstweb.demo.pojo.CommentPOJO;
import com.firstweb.demo.pojo.ResultCodePOJO;
import com.firstweb.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    public Object post(@RequestBody CommentPOJO commentPOJO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultCodePOJO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentPOJO.getParentId());
        comment.setContent(commentPOJO.getContent());
        comment.setType(commentPOJO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setCommentator((long) 1);
        comment.setLikeCount((long) 0);
        commentService.insert(comment);
        return ResultCodePOJO.okOf();
    }
}
