package com.firstweb.demo.controller;

import com.firstweb.demo.pojo.QuestionPOJO;
import com.firstweb.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zxx
 * @2019/7/23 16:04
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id,
                           Model model){
        QuestionPOJO questionPOJO=questionService.getById(id);
        //累加阅读数
        questionService.intView(id);
        model.addAttribute("question",questionPOJO);
        return "question";
    }
}
