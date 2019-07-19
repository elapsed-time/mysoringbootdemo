package com.firstweb.demo.model;

import lombok.Data;

/**
 * @author zxx
 * @2019/7/17 15:56
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private long gmtCreate;
    private long gmtModified;
    private String avatarUrl;
}
