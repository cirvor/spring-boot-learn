package com.cirvor.learn.controller;

import com.cirvor.learn.util.ResultUtil;
import com.cirvor.learn.vo.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public ResultData home()
    {
        return ResultUtil.success("Home Page");
    }

    @GetMapping("hello")
    public ResultData hello()
    {
        return ResultUtil.success("Hello World");
    }

    @GetMapping("success")
    public ResultData success()
    {
        return ResultUtil.success();
    }
}
