package com.firstweb.demo.controller;

import com.firstweb.demo.pojo.PagePOJO;
import com.firstweb.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zxx
 * @2019/7/16 15:30
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(@RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        Model model) {
        PagePOJO pagePOJO = questionService.list(page, size);
        model.addAttribute("pagePOJO", pagePOJO);
        return "index";
    }
}
