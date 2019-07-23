package com.firstweb.demo.controller;

import com.firstweb.demo.mapper.QuestionMapper;
import com.firstweb.demo.model.Question;
import com.firstweb.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zxx
 * @2019/7/18 11:07
 */
@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model) {
        //将数据写入model
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        //判断数据
        if(title.isEmpty()){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description.isEmpty()){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if(tag.isEmpty()){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        //登录成功才将数据存入数据库
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        questionMapper.create(question);
        return "redirect:/";
    }
}
