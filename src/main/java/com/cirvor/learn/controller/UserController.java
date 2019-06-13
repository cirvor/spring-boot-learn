package com.cirvor.learn.controller;

import com.cirvor.learn.pojo.User;
import com.cirvor.learn.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public User user(@RequestParam(value="name") String name) {
        return userService.selectUserByName(name);
    }

    @GetMapping("get")
    public User user(@RequestParam(value="id") int id) {
        return userService.find(id);
    }

    /**
     * 使用分页器
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("list")
    public PageInfo<User> userList(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(userService.selectAllUser());
    }

    @GetMapping("all")
    public List<User> all() {
        return userService.all();
    }
}
