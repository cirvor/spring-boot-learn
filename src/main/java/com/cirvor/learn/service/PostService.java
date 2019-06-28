package com.cirvor.learn.service;

import com.cirvor.learn.pojo.Post;

import java.util.List;

public interface PostService {
    Post find(int id);

    List<Post> all();
}
