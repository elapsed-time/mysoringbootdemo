package com.firstweb.demo.pojo;

import lombok.Data;

/**
 * @author zxx
 * @2019/7/17 9:34
 */
@Data
public class GithubUserPOJO {
    private String name;
    private long id;
    private String bio;

    private String avatar_url;
}
