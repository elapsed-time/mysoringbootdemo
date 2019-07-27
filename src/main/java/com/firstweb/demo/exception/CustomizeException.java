package com.firstweb.demo.exception;

/**
 * @author zxx
 * @2019/7/26 9:53
 */
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;
    public CustomizeException(ICustomizeErrorCode errorCode){
        this.code=errorCode.getCode();
        this.message=errorCode.getMessage();
    }
   /* public CustomizeException(String message) {
        this.message = message;
    }*/

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
