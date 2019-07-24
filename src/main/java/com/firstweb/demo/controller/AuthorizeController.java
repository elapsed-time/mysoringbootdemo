package com.firstweb.demo.controller;

import com.firstweb.demo.mapper.UserMapper;
import com.firstweb.demo.model.User;
import com.firstweb.demo.pojo.AccessTokenPOJO;
import com.firstweb.demo.pojo.GithubUserPOJO;
import com.firstweb.demo.provider.GithubProvider;
import com.firstweb.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author zxx
 * @2019/7/17 8:31
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubprovider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String cilentSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenPOJO accessTokenPOJO = new AccessTokenPOJO();//将调用github接口获得到的数据封装，为调用下一个github接口做准备
        accessTokenPOJO.setCode(code);
        accessTokenPOJO.setRedirect_uri(redirectUri);
        accessTokenPOJO.setState(state);
        accessTokenPOJO.setClient_id(clientId);
        accessTokenPOJO.setClient_secret(cilentSecret);
        String accessToken = githubprovider.getAccessToken(accessTokenPOJO);
        GithubUserPOJO githubUserPOJO = githubprovider.getUser(accessToken);
        if (githubUserPOJO != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUserPOJO.getName());
            user.setAccountId(String.valueOf(githubUserPOJO.getId()));
            user.setAvatarUrl(githubUserPOJO.getAvatar_url());
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token", token));//将token写入到cookie
            return "redirect:/";
        } else {
            //登录失败，重新登录
            return "redirect:/";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
