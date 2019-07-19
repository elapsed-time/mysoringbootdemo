package com.firstweb.demo.model;

import lombok.Data;

/**
 * @author zxx
 * @2019/7/18 14:46
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;

}
