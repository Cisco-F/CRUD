package com.murrrphy.service;

import com.murrrphy.pojo.Post;
import com.murrrphy.pojo.User;

import java.util.List;

//服务层接口
public interface PostService {
    //登录
    User login(User user);

    //查询所有文章
    List<Post> list();

    void delete(List<Integer> ids);

    void add(Post post);

    Post getById(Integer id);

    void update(Post post);
}
