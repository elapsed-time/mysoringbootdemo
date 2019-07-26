package com.firstweb.demo.pojo;

import lombok.Data;

/**
 * @author zxx
 * @2019/7/26 15:45
 */
@Data
public class CommentPOJO {
    private long parentId;
    private String content;
    private Integer type;
}
