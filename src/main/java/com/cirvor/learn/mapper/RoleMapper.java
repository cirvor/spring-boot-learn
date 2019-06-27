package com.cirvor.learn.mapper;

import com.cirvor.learn.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
@Component
public interface RoleMapper extends BaseMapper<Role> {
}