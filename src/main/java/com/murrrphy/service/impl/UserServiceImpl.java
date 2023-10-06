package com.murrrphy.service.impl;

import com.murrrphy.mapper.UserMapper;
import com.murrrphy.pojo.User;
import com.murrrphy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    //登录
    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    //注册
    @Override
    public void register(User user) {
        //设置默认level为2
        user.setLevel(2);
        userMapper.register(user);
    }
}
