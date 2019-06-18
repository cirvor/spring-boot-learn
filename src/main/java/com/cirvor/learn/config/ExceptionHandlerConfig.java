package com.cirvor.learn.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerConfig  extends ResponseEntityExceptionHandler {
    /**
     * 处理Exception异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Object handleException(Exception e) {
        Map<String, Object> result = new HashMap<>();

        if (e instanceof NoHandlerFoundException) {
            System.out.println(111111);
            result.put("code", 404);
            result.put("msg", e.getMessage());
        } else {
            result.put("code", 500);
            result.put("msg", e.getMessage());
        }

        return result;
    }
}
