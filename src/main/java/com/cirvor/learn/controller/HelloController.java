package com.cirvor.learn.controller;

import com.cirvor.learn.utils.ResultUtils;
import com.cirvor.learn.vo.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home()
    {
        return "Home Page";
    }

    @GetMapping("hello")
    public String hello()
    {
        return "Hello World";
    }

    @GetMapping("success")
    public ResultData success()
    {
//        return ResultUtils.success();
        return ResultUtils.success("success");
    }
}
