package com.cirvor.learn.service;

import com.cirvor.learn.pojo.User;
import com.cirvor.learn.pojo.UserPost;
import com.cirvor.learn.pojo.UserRole;
import com.github.pagehelper.Page;

import java.util.List;

public interface UserService {
    User selectUserByUsername(String name);

    List<User> getUserByUsername(String name);

    Page<User> selectAllUser();

    User find(int id);

    UserPost findUserWithPosts(int id);

    UserRole findUserWithRoles(int id);

    List<User> all();
}
