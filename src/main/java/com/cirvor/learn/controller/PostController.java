package com.cirvor.learn.controller;

import com.cirvor.learn.pojo.Post;
import com.cirvor.learn.service.PostService;
import com.cirvor.learn.util.ResultUtil;
import com.cirvor.learn.vo.ResultData;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    /**
     * 根据id获取文章内容
     *
     * @param request 请求参数
     * @return ResultData
     * @throws NumberFormatException 转换异常
     * @throws NotFoundException 找不到对象
     */
    @GetMapping("get")
    public ResultData user(HttpServletRequest request) throws
            NumberFormatException,
            NotFoundException {
        //获取参数id
        int id = Integer.parseInt(request.getParameter("id"));
        if (id < 1) throw new NumberFormatException("ID过小");
        Post post = postService.find(id);
        Optional<Post> postOptional = Optional.ofNullable(post);

        return postOptional
                .map(ResultUtil::success)
                .orElseThrow(() -> new NotFoundException("用户不存在"));
    }
}
