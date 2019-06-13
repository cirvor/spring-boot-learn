package com.cirvor.learn.service.impl;

import com.cirvor.learn.mapper.UserMapper;
import com.cirvor.learn.pojo.User;
import com.cirvor.learn.service.UserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据名字查找用户
     */
    public User selectUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    /**
     * 查找所有用户
     */
    public Page<User> selectAllUser() {
        return userMapper.findAllUser();
    }

    /**
     * 根据id查找
     * @param id 主键
     * @return 用户
     */
    @Cacheable(value = "user", key = "#id")
    public User find(int id) {
        return  userMapper.selectByPrimaryKey(id);
    }

    public List<User> all()
    {
        return  userMapper.selectAll();
    }
//
//    /**
//     * 插入两个用户
//     */
//    public void insertService() {
//        userMapper.insertUser("SnailClimb", 22, 3000.0);
//        userMapper.insertUser("Daisy", 19, 3000.0);
//    }
//
//    /**
//     * 根据id 删除用户
//     */
//
//    public void deleteService(int id) {
//        userMapper.deleteUser(id);
//    }
//
//    /**
//     * 模拟事务。由于加上了 @Transactional注解，如果转账中途出了意外 SnailClimb 和 Daisy 的钱都不会改变。
//     */
//    @Transactional
//    public void changemoney() {
//        userMapper.updateUser("SnailClimb", 22, 2000.0, 3);
//        // 模拟转账过程中可能遇到的意外状况
//        int temp = 1 / 0;
//        userMapper.updateUser("Daisy", 19, 4000.0, 4);
//    }
}
