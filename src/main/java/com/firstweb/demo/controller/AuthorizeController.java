package com.firstweb.demo.controller;

import com.firstweb.demo.pojo.AccessTokenPOJO;
import com.firstweb.demo.pojo.GithubUserPOJO;
import com.firstweb.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String cilentSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenPOJO accessTokenPOJO=new AccessTokenPOJO();
        accessTokenPOJO.setCode(code);
        accessTokenPOJO.setRedirect_uri(redirectUri);
        accessTokenPOJO.setState(state);
        accessTokenPOJO.setClient_id(clientId);
        accessTokenPOJO.setClient_secret(cilentSecret);
        String accessToken=githubprovider.getAccessToken(accessTokenPOJO);
        GithubUserPOJO user=githubprovider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
