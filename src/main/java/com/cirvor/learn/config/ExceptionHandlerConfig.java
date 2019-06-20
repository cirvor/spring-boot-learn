package com.cirvor.learn.config;

import com.cirvor.learn.utils.HttpEnum;
import com.cirvor.learn.utils.ResultUtils;
import com.cirvor.learn.vo.ResultData;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerConfig  extends ResponseEntityExceptionHandler {

    /**
     * 处理Exception异常
     *
     * @param e 异常对象
     * @return ResultData
     */
    @ExceptionHandler(value = Exception.class)
    public ResultData handleException(Exception e) {
        HttpEnum httpEnum;
        String msg;

        if (e instanceof NoHandlerFoundException) {
            httpEnum = HttpEnum.NOT_FOUND;
        } else if (e instanceof NotFoundException) {
            httpEnum = HttpEnum.NOT_FOUND;
        } else if (e instanceof NumberFormatException) {
            httpEnum = HttpEnum.BAD_REQUEST;
        } else {
            httpEnum = HttpEnum.ERROR;
        }
        msg = e.getMessage();

        return ResultUtils.error(httpEnum, msg);
    }
}
