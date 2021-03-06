package com.firstweb.demo.controller;

import com.firstweb.demo.model.User;
import com.firstweb.demo.pojo.PagePOJO;
import com.firstweb.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zxx
 * @2019/7/22 13:37
 */
@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          Model model) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }
        PagePOJO pagePOJO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagePOJO", pagePOJO);
        return "profile";
    }
}
