package com.cirvor.learn.service.impl;

import com.cirvor.learn.mapper.RoleMapper;
import com.cirvor.learn.pojo.Role;
import com.cirvor.learn.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    /**
     * 获取角色
     *
     * @param id
     * @return
     */
    public Role find(int id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
