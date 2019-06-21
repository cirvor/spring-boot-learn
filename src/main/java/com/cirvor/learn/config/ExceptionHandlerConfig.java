package com.cirvor.learn.config;

import com.cirvor.learn.utils.HttpEnum;
import com.cirvor.learn.utils.ResultUtils;
import com.cirvor.learn.vo.ResultData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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

        switch (e.getClass().getSimpleName()) {
            case "NoHandlerFoundException":
                httpEnum = HttpEnum.NOT_FOUND;
                break;
            case "NotFoundException":
                httpEnum = HttpEnum.NOT_FOUND;
                break;
            case "NumberFormatException":
                httpEnum = HttpEnum.BAD_REQUEST;
                break;
            case "ConstraintViolationException":
                httpEnum = HttpEnum.BAD_REQUEST;
                break;
            default:
                httpEnum = HttpEnum.ERROR;
        }
        msg = e.getMessage();

        return ResultUtils.error(httpEnum, msg);
    }
}
