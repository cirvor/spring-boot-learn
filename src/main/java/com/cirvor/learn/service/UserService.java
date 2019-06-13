package com.cirvor.learn.service;

import com.cirvor.learn.pojo.User;
import com.github.pagehelper.Page;

import java.util.List;

public interface UserService {
    User selectUserByName(String name);

    Page<User> selectAllUser();

    User find(int id);

    List<User> all();
}
