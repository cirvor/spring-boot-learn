package com.cirvor.learn.controller;

import com.cirvor.learn.pojo.User;
import com.cirvor.learn.service.UserService;
import com.cirvor.learn.utils.ResultUtils;
import com.cirvor.learn.vo.ResultData;
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

    /**
     * 根据name获取用户数据
     *
     * @param name 用户名
     * @return ResultData
     */
    @GetMapping
    public ResultData user(@RequestParam(value="name") String name) {
        User user = userService.selectUserByName(name);
        return ResultUtils.success(user);
    }

    /**
     * 根据id获取用户数据
     * @param id ID
     * @return ResultData
     */
    @GetMapping("get")
    public ResultData user(@RequestParam(value="id") int id) {
        User user = userService.find(id);
        return ResultUtils.success(user);
    }

    /**
     * 分页获取所有用户数据
     *
     * @param pageNo 页数，默认为1
     * @param pageSize 每页数量，默认为10
     * @return ResultData
     */
    @GetMapping("list")
    public ResultData userList(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<User> userPageInfo = new PageInfo<>(userService.selectAllUser());
        return ResultUtils.success(userPageInfo);
    }

    /**
     * 获取所有用户
     *
     * @return ResultData
     */
    @GetMapping("all")
    public ResultData all() {
        List<User> userList = userService.all();
        return ResultUtils.success(userList);
    }
}
