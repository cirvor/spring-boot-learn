package com.cirvor.learn.mapper;

import com.cirvor.learn.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
@Component(value = "postMapper")
public interface PostMapper extends BaseMapper<Post> {
}