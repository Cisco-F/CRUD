package com.murrrphy.service.impl;

import com.murrrphy.mapper.PostMapper;
import com.murrrphy.pojo.Post;
import com.murrrphy.pojo.User;
import com.murrrphy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service//服务层
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    //登录
    @Override
    public User login(User user) {
        return postMapper.login(user);
    }

    //查询所有文章
    @Override
    public List<Post> list() {
        return postMapper.list();
    }

    //（批量）删除
    @Override
    public void delete(List<Integer> ids) {
        postMapper.delete(ids);
    }

    //添加文章
    @Override
    public void add(Post post) {
        //设置创建时间和更新时间
        post.setCreateTime(LocalDateTime.now());
        post.setUpdateTime(LocalDateTime.now());
        postMapper.add(post);
    }

    //根据id查询
    @Override
    public Post getById(Integer id) {
        return postMapper.getById(id);
    }

    //更新文章
    @Override
    public void update(Post post) {
        postMapper.update(post);
    }
}
