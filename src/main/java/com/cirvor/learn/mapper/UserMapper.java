package com.cirvor.learn.mapper;

import com.cirvor.learn.pojo.User;
import com.cirvor.learn.pojo.UserPost;
import com.cirvor.learn.pojo.UserRole;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {
    /**
     * 通过名字查询用户信息
     */
    User findUserByUsername(String username);

    /**
     * 查询所有用户信息
     */
    @Select("SELECT * FROM user")
    Page<User> findAllUser();

    /**
     * 查询所有用户信息
     */
    @Select("SELECT * FROM user where username=#{username}")
    List<User> getUserByUsername(String username);

    UserPost findUserWithPosts(int id);

    UserRole findUserWithRoles(int id);

//    /**
//     * 插入用户信息
//     */
//    @Insert("INSERT INTO user(name, age,money) VALUES(#{name}, #{age}, #{money})")
//    void insertUser(@Param("name") String name, @Param("age") Integer age, @Param("money") Double money);
//
//    /**
//     * 根据 id 更新用户信息
//     */
//    @Update("UPDATE  user SET name = #{name},age = #{age},money= #{money} WHERE id = #{id}")
//    void updateUser(@Param("name") String name, @Param("age") Integer age, @Param("money") Double money,
//                    @Param("id") int id);
//
//    /**
//     * 根据 id 删除用户信息
//     */
//    @Delete("DELETE from user WHERE id = #{id}")
//    void deleteUser(@Param("id") int id);
}
