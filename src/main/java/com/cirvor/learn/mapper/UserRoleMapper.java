package com.cirvor.learn.mapper;

import com.cirvor.learn.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
@Component
public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * 查询所有用户信息
     */
    @Select("SELECT * FROM user_role where user_id=#{user_id}")
    List<UserRole> listByUserId(int user_id);
}