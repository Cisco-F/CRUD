package com.murrrphy.service;

import com.murrrphy.pojo.User;

public interface UserService {
    //登录
    User login(User user);

    //注册
    void register(User user);
}
