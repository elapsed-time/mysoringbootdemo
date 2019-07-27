package com.firstweb.demo.pojo;

import com.firstweb.demo.model.User;
import lombok.Data;

/**
 * @author zxx
 * @2019/7/19 10:40
 */
@Data
public class QuestionPOJO {
    private long id;
    private String title;
    private String description;
    private String tag;
    private long gmtCreate;
    private long gmtModified;
    private long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
