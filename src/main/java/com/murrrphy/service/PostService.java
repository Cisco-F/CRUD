package com.murrrphy.service;

import com.murrrphy.pojo.Post;
import com.murrrphy.pojo.Result;
import com.murrrphy.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//服务层接口
public interface PostService {

    //查询所有文章
    List<Post> list(HttpServletRequest request);

    //（批量）删除
    void delete(List<Integer> ids);

    //添加文章
    void add(Post post);

    //根据id查询
    Post getById(Integer id);

    //更新文章
    Result update(Post post, HttpServletRequest request);
}
