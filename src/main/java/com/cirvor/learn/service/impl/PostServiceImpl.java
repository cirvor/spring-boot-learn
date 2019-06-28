package com.cirvor.learn.service.impl;

import com.cirvor.learn.mapper.PostMapper;
import com.cirvor.learn.mapper.UserMapper;
import com.cirvor.learn.pojo.Post;
import com.cirvor.learn.pojo.User;
import com.cirvor.learn.service.PostService;
import com.cirvor.learn.service.UserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;

    @Autowired
    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    /**
     * 根据id查找
     * @param id 主键
     * @return 文章
     */
    public Post find(int id) {
        return  postMapper.findById(id);
    }

    public List<Post> all() {
        return postMapper.all();
    }
}
