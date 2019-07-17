package com.firstweb.demo.controller;

import com.firstweb.demo.pojo.AccessTokenPOJO;
import com.firstweb.demo.pojo.GithubUserPOJO;
import com.firstweb.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zxx
 * @2019/7/17 8:31
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubprovider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenPOJO accessTokenPOJO=new AccessTokenPOJO();
        accessTokenPOJO.setCode(code);
        accessTokenPOJO.setRedirect_uri("http://localhost:4396/callback");
        accessTokenPOJO.setState(state);
        accessTokenPOJO.setClient_id("5968f58600b7ef5001ee");
        accessTokenPOJO.setClient_secret("8f46f2052e7dba4ad6ad4405d5e4c3cdf453b59a");
        String accessToken=githubprovider.getAccessToken(accessTokenPOJO);
        GithubUserPOJO user=githubprovider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
