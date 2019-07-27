package com.firstweb.demo.advice;

import com.alibaba.fastjson.JSON;
import com.firstweb.demo.exception.CustomizeErrorCode;
import com.firstweb.demo.exception.CustomizeException;
import com.firstweb.demo.pojo.ResultCodePOJO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zxx
 * @2019/7/26 9:35
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            //返回 JSON
            ResultCodePOJO resultCodePOJO;
            if (e instanceof CustomizeException) {
                resultCodePOJO = (ResultCodePOJO) ResultCodePOJO.errorOf((CustomizeException) e);
            } else {
                resultCodePOJO = ResultCodePOJO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }

            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = null;
                writer = response.getWriter();
                writer.write(JSON.toJSONString(resultCodePOJO));
                writer.close();
            } catch (IOException ioe) {
            }
            return null;

        } else {
            //错误页面跳转
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR);
            }
            return new ModelAndView("error");
        }
    }
}
