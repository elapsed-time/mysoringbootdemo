package com.firstweb.demo.pojo;

import com.firstweb.demo.exception.CustomizeErrorCode;
import com.firstweb.demo.exception.CustomizeException;
import lombok.Data;

/**
 * @author zxx
 * @2019/7/26 17:12
 */
@Data
public class ResultCodePOJO {
    private Integer code;
    private String message;
    public static ResultCodePOJO errorOf(Integer code,String message){
        ResultCodePOJO resultCodePOJO = new ResultCodePOJO();
        resultCodePOJO.setCode(code);
        resultCodePOJO.setMessage(message);
        return resultCodePOJO;
    }

    public static ResultCodePOJO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }
    public static ResultCodePOJO okOf(){
        ResultCodePOJO resultCodePOJO=new ResultCodePOJO();
        resultCodePOJO.setCode(200);
        resultCodePOJO.setMessage("请求成功");
        return resultCodePOJO;
    }

    public static Object errorOf(CustomizeException e) {
        return errorOf(e.getCode(),e.getMessage());
    }
}
