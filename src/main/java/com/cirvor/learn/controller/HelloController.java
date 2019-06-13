package com.cirvor.learn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String home()
    {
        return "Home Page";
    }

    @RequestMapping("/hello")
    public String hello()
    {
        return "Hello World";
    }
}
