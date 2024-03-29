package com.cirvor.learn.controller;

import com.cirvor.learn.util.HttpEnum;
import com.cirvor.learn.util.ResultUtil;
import com.cirvor.learn.vo.ResultData;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
public class BasicErrorController implements ErrorController {

    /**
     * 处理异常状态
     *
     * @param request 请求信息
     * @return ResultData
     */
    @RequestMapping("/error")
    public ResultData handleError(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 400) {
            return ResultUtil.error(HttpEnum.BAD_REQUEST);
        } else if (statusCode == 401) {
            return ResultUtil.error(HttpEnum.UNAUTHORIZED);
        } else if(statusCode == 404) {
            String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
            return ResultUtil.error(HttpEnum.NOT_FOUND, requestUri + " is not handled");
        } else {
            String msg = (String) request.getAttribute("javax.servlet.error.message");
            return ResultUtil.error(HttpEnum.NOT_FOUND, msg);
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
