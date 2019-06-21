package com.cirvor.learn.controller;

import com.cirvor.learn.pojo.User;
import com.cirvor.learn.service.UserService;
import com.cirvor.learn.utils.ResultUtils;
import com.cirvor.learn.vo.ResultData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

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
    public ResultData user(@RequestParam(value="name") String name) throws
            NotFoundException {
        User user = userService.selectUserByName(name);
        Optional<User> userOptional = Optional.ofNullable(user);

        return userOptional
                .map(ResultUtils::success)
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
        String id = request.getParameter("id");
        User user = userService.find(Integer.parseInt(id));
        Optional<User> userOptional = Optional.ofNullable(user);

        return userOptional
                .map(ResultUtils::success)
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
