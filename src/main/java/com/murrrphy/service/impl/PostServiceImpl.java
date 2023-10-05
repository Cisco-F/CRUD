package com.murrrphy.service.impl;

import com.murrrphy.mapper.PostMapper;
import com.murrrphy.pojo.Post;
import com.murrrphy.pojo.User;
import com.murrrphy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public User login(User user) {
        return postMapper.login(user);
    }

    @Override
    public List<Post> list() {
        return postMapper.list();
    }

    @Override
    public void delete(List<Integer> ids) {
        postMapper.delete(ids);
    }

    @Override
    public void add(Post post) {
        //设置创建时间和更新时间
        post.setCreateTime(LocalDateTime.now());
        post.setUpdateTime(LocalDateTime.now());
        postMapper.add(post);
    }

    @Override
    public Post getById(Integer id) {
        return postMapper.getById(id);
    }

    @Override
    public void update(Post post) {
        postMapper.update(post);
    }
}
