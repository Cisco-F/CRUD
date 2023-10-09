package com.murrrphy.service;

import com.murrrphy.pojo.Result;
import com.murrrphy.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    //登录
    User login(User user);

    //注册
    Result register(User user);

    //注销
    Result logOff(Integer id, HttpServletRequest request);

    //查询所有用户
    Result list(HttpServletRequest request);

    //根据id查询用户
    User getById(Integer id);

    //修改密码
    Result updatePassword(User user, HttpServletRequest request);
}
