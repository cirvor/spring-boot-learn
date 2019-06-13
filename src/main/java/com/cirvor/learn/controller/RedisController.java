package com.cirvor.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

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

    /**
     * 键值对
     *
     * @param value
     * @return
     */
    @GetMapping("key")
    public String key(@RequestParam(defaultValue = "value") String value) {
        stringRedisTemplate.opsForValue().set("k1", value);
        return stringRedisTemplate.opsForValue().get("k1");
    }

    /**
     * Hash
     *
     * @param value
     * @return
     */
    @GetMapping("hash")
    public Object hash(@RequestParam(defaultValue = "value") String value) {
        stringRedisTemplate.opsForHash().put("h1","map1", value);
        return stringRedisTemplate.opsForHash().get("h1","map1");
    }

    /**
     * 列表
     *
     * @param value
     * @return
     */
    @GetMapping("list")
    public String list(@RequestParam(defaultValue = "value") String value) {
        stringRedisTemplate.opsForList().leftPush("l1", value);
        return stringRedisTemplate.opsForList().rightPop("l1");
    }

    /**
     * 集合
     *
     * @param value
     * @return
     */
    @GetMapping("set")
    public Set<String> set(@RequestParam(defaultValue = "value") String value) {
        stringRedisTemplate.opsForSet().add("s1", value);
        return stringRedisTemplate.opsForSet().members("s1");
    }

    /**
     * 有序集合
     *
     * @param value
     * @return
     */
    @GetMapping("zset")
    public Set<String> zSet(@RequestParam(defaultValue = "value") String value, @RequestParam(defaultValue = "1") int score) {
        System.out.println(score);
        stringRedisTemplate.opsForZSet().add("zs1", value, score);
        return stringRedisTemplate.opsForZSet().range("zs1", 0, -1);
    }
}
