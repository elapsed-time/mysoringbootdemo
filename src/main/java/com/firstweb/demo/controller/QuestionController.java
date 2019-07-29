package com.firstweb.demo.controller;

import com.firstweb.demo.enums.CommentTypeEnum;
import com.firstweb.demo.pojo.CommentPOJO;
import com.firstweb.demo.pojo.QuestionPOJO;
import com.firstweb.demo.service.CommentService;
import com.firstweb.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author zxx
 * @2019/7/23 16:04
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") long id,
                           Model model) {
        QuestionPOJO questionPOJO = questionService.getById(id);
        List<CommentPOJO> commentCreatePOJOS = commentService.ListByTarget(id, CommentTypeEnum.QUESTION);
        //累加阅读数
        questionService.intView(id);
        model.addAttribute("question", questionPOJO);
        model.addAttribute("commentCreatePOJOS", commentCreatePOJOS);
        return "question";
    }
}
