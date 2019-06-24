package com.cirvor.learn.config;

import com.cirvor.learn.utils.HttpEnum;
import com.cirvor.learn.utils.ResultUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;

@ControllerAdvice
public class EntityExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    /**
     * 处理全局异常
     *
     * @param e 全局异常
     * @param body 返回数据
     * @param headers 请求头
     * @param status 异常状态
     * @param request 请求参数
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {

        return new ResponseEntity<Object>(ResultUtils.error(HttpEnum.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理参数绑定异常
     *
     * @param e 绑定异常
     * @param headers 请求头
     * @param status 异常状态
     * @param request 请求参数
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleBindException(BindException e,
                                                         HttpHeaders headers,
                                                         HttpStatus status,
                                                         WebRequest request) {
        return new ResponseEntity<Object>(ResultUtils.error(HttpEnum.BAD_REQUEST, "BindException:" + buildMessages(e.getBindingResult())),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理请求参数验证失败异常
     *
     * @param e 验证失败异常
     * @param headers 请求头
     * @param status 异常状态
     * @param request 请求参数
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        return new ResponseEntity<Object>(ResultUtils.error(HttpEnum.BAD_REQUEST, "MethodArgumentNotValid:" + buildMessages(e.getBindingResult())),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理缺少参数异常
     *
     * @param e 缺少参数异常
     * @param headers 请求头
     * @param status 异常状态
     * @param request 请求参数
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e,
                                                                       HttpHeaders headers,
                                                                       HttpStatus status,
                                                                       WebRequest request) {
        return new ResponseEntity<Object>(ResultUtils.error(HttpEnum.BAD_REQUEST, "ParamMissing:" + e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理参数匹配失败异常
     *
     * @param e 参数匹配失败异常
     * @param headers 请求头
     * @param status 异常状态
     * @param request 请求参数
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException e,
                                                        HttpHeaders headers,
                                                        HttpStatus status,
                                                        WebRequest request) {
        return new ResponseEntity<Object>(ResultUtils.error(HttpEnum.BAD_REQUEST, "TypeMissMatch:" + e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * 将List数据转为String
     *
     * @param result 数据
     * @return 字符串
     */
    private String buildMessages(BindingResult result) {
        StringBuilder resultBuilder = new StringBuilder();

        List<ObjectError> errors = result.getAllErrors();
        if (errors != null && errors.size() > 0) {
            for (ObjectError error : errors) {
                if (error instanceof FieldError) {
                    FieldError fieldError = (FieldError) error;
                    String fieldName = fieldError.getField();
                    String fieldErrMsg = fieldError.getDefaultMessage();
                    resultBuilder.append(fieldName).append(" ").append(fieldErrMsg).append(";");
                }
            }
        }
        return resultBuilder.toString();
    }
}
