package com.firstweb.demo.controller;

import com.firstweb.demo.mapper.QuestionMapper;
import com.firstweb.demo.mapper.UserMapper;
import com.firstweb.demo.model.User;
import com.firstweb.demo.pojo.PagePOJO;
import com.firstweb.demo.pojo.QuestionPOJO;
import com.firstweb.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zxx
 * @2019/7/16 15:30
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;//报错但不影响运行

    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size) {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findbyToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        PagePOJO pagePOJO=questionService.list(page,size);
        model.addAttribute("pagePOJO",pagePOJO);
        return "index";
    }
}
