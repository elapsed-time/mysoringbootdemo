package com.firstweb.demo.pojo;

import lombok.Data;

/**
 * @author zxx
 * @2019/7/17 8:52
 */
@Data
public class AccessTokenPOJO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
