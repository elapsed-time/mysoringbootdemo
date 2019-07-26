package com.firstweb.demo.exception;

/**
 * @author zxx
 * @2019/7/26 10:11
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("你找的问题被外星人偷走了，换个试试吧！");
    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
