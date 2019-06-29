package com.cirvor.learn.controller;

import com.cirvor.learn.pojo.User;
import com.cirvor.learn.pojo.UserPost;
import com.cirvor.learn.service.UserRoleService;
import com.cirvor.learn.service.UserService;
import com.cirvor.learn.util.ResultUtil;
import com.cirvor.learn.vo.ResultData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final UserRoleService userRoleService;

    @Autowired
    public UserController(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    /**
     * 根据name获取用户数据
     *
     * @param name 用户名
     * @return ResultData
     */
    @GetMapping("name/{name}")
    public ResultData user(@PathVariable("name") String name) throws
            NotFoundException {
        User user = userService.selectUserByUsername(name);
        Optional<User> userOptional = Optional.ofNullable(user);

        return userOptional
                .map(ResultUtil::success)
                .orElseThrow(() -> new NotFoundException("用户不存在"));
    }

    /**
     * 根据id获取用户数据
     *
     * @param request 请求参数
     * @return ResultData
     * @throws NumberFormatException 转换异常
     * @throws NotFoundException 找不到对象
     */
    @GetMapping("get")
    public ResultData user(HttpServletRequest request) throws
            NumberFormatException,
            NotFoundException {
        //获取参数id
        int id = Integer.parseInt(request.getParameter("id"));
        if (id < 1) throw new NumberFormatException("ID过小");
        User user = userService.find(id);
        Optional<User> userOptional = Optional.ofNullable(user);

        return userOptional
                .map(ResultUtil::success)
                .orElseThrow(() -> new NotFoundException("用户不存在"));
    }

    /**
     * 根据id获取用户数据
     *
     * @param id 请求参数
     * @return ResultData
     * @throws NumberFormatException 转换异常
     * @throws NotFoundException 找不到对象
     */
    @GetMapping("user-info")
    public ResultData userInfo(@RequestParam(value = "id") int id) throws NumberFormatException, NotFoundException {
        UserPost userPost = userService.findUserWithPosts(id);
        Optional<UserPost> userOptional = Optional.ofNullable(userPost);

        return userOptional
                .map(ResultUtil::success)
                .orElseThrow(() -> new NotFoundException("用户不存在"));
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

        return ResultUtil.success(userPageInfo);
    }

    /**
     * 获取所有用户
     *
     * @return ResultData
     */
    @GetMapping("all")
    public ResultData all() {
        List<User> userList = userService.all();

        return ResultUtil.success(userList);
    }

    /**
     * 获取所有请求
     *
     * @param value 请求参数
     * @return ResultData
     */
    @PostMapping("info")
    public ResultData info(@RequestParam Map<String, String> value) {
        return ResultUtil.success(value);
    }

    @GetMapping("role")
    public ResultData role(@RequestParam int user_id) {
        return ResultUtil.success(userRoleService.listByUserId(user_id));
    }
}
