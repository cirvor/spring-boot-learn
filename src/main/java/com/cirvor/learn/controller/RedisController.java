package com.cirvor.learn.controller;

import com.cirvor.learn.utils.ResultUtils;
import com.cirvor.learn.vo.ResultData;
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

    @GetMapping
    public ResultData index() {
        return ResultUtils.success("Redis index");
    }

    /**
     * 键值对
     *
     * @param value
     * @return
     */
    @GetMapping("key")
    public ResultData key(@RequestParam(defaultValue = "value") String value) {
        stringRedisTemplate.opsForValue().set("k1", value);
        Object cache = stringRedisTemplate.opsForValue().get("k1");

        return ResultUtils.success(cache);
    }

    /**
     * Hash
     *
     * @param value
     * @return
     */
    @GetMapping("hash")
    public ResultData hash(@RequestParam(defaultValue = "value") String value) {
        stringRedisTemplate.opsForHash().put("h1","map1", value);
        Object cache = stringRedisTemplate.opsForHash().get("h1","map1");

        return ResultUtils.success(cache);
    }

    /**
     * 列表
     *
     * @param value
     * @return
     */
    @GetMapping("list")
    public ResultData list(@RequestParam(defaultValue = "value") String value) {
        stringRedisTemplate.opsForList().leftPush("l1", value);
        Object cache = stringRedisTemplate.opsForList().rightPop("l1");

        return ResultUtils.success(cache);
    }

    /**
     * 集合
     *
     * @param value
     * @return
     */
    @GetMapping("set")
    public ResultData set(@RequestParam(defaultValue = "value") String value) {
        stringRedisTemplate.opsForSet().add("s1", value);
        Set<String> cacheList = stringRedisTemplate.opsForSet().members("s1");

        return ResultUtils.success(cacheList);
    }

    /**
     * 有序集合
     *
     * @param value
     * @return
     */
    @GetMapping("zset")
    public ResultData zSet(@RequestParam(defaultValue = "value") String value, @RequestParam(defaultValue = "1") int score) {
        System.out.println(score);
        stringRedisTemplate.opsForZSet().add("zs1", value, score);
        Set<String> cacheList = stringRedisTemplate.opsForZSet().range("zs1", 0, -1);
        return ResultUtils.success(cacheList);
    }
}
