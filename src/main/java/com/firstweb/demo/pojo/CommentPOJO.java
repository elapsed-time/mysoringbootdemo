package com.firstweb.demo.pojo;

import com.firstweb.demo.model.User;
import lombok.Data;

/**
 * @author zxx
 * @2019/7/27 15:35
 */
@Data
public class CommentPOJO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
}
