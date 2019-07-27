package com.firstweb.demo.exception;

/**
 * @author zxx
 * @2019/7/26 10:11
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(210,"你找的问题被外星人偷走了，换个试试吧！"),
    NO_LOGIN(211,"当前操作需要登录，请登录后重试"),
    SYS_ERROR(212,"服务器冒烟了，请稍等！！！"),
    TYPE_PARAM_WRONG(213,"评论类型错误或不存在"),
    TARGET_PARAM_NOT_FOUND(214,"没有选择问题或者评论"),
    COMMENT_NOT_FOUNT (215,"你回复的评论不在了，换一个吧"),
    QUESTION_NOT_FOUNT (215,"你回复的问题不在了，换一个吧"),
    ;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code,String message) {
        this.message = message;
        this.code=code;
    }
}
