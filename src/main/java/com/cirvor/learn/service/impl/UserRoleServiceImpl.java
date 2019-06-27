package com.cirvor.learn.service.impl;

import com.cirvor.learn.mapper.RoleMapper;
import com.cirvor.learn.mapper.UserRoleMapper;
import com.cirvor.learn.pojo.Role;
import com.cirvor.learn.pojo.UserRole;
import com.cirvor.learn.service.RoleService;
import com.cirvor.learn.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private UserRoleMapper userRoleMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    /**
     * 获取角色
     *
     * @param user_id
     * @return
     */
    public List<UserRole> listByUserId(int user_id) {
        return userRoleMapper.listByUserId(user_id);
    }
}
