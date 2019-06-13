package com.cirvor.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping("")
    public String index() {
        return "Redis index";
    }

    @GetMapping("key")
    public String key() {
        stringRedisTemplate.opsForValue().set("k1", "v1");
        return "Redis key";
    }
}
