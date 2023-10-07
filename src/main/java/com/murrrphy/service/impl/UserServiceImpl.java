package com.murrrphy.service.impl;

import com.murrrphy.mapper.UserMapper;
import com.murrrphy.pojo.Result;
import com.murrrphy.pojo.User;
import com.murrrphy.service.UserService;
import com.murrrphy.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    //注销功能
    @Override
    public Result logOff(Integer id, HttpServletRequest request) {
        //获得当前登录用户信息
        Claims claims = JwtUtils.getData(request);
        int currentUserLevel = (Integer) claims.get("level");
        String username = (String) claims.get("username");
        String password = (String) claims.get("password");
        User u = userMapper.getById(id);

        if(u == null){//请求的id对应用户可能不存在
            return Result.error("用户不存在！");
        } else if (currentUserLevel == 0) {//请求用户存在，当前登录为管理员则正常删除
            userMapper.logOff(id);
            return Result.success();
        } else if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
            userMapper.logOff(id);
            return Result.success();
        }else {//以上情况均不符合，禁止操作
            return Result.error("权限不足！");
        }
    }

    //查询所有用户
    @Override
    public Result list(HttpServletRequest request) {
        //先判断是不是管理员
        Claims claims = JwtUtils.getData(request);
        int currentUserLevel = (Integer) claims.get("level");
        if(currentUserLevel == 0){
            List<User> userList = userMapper.list();
            return Result.success(userList);
        }
        else{
            return Result.error("权限不足！");
        }
    }

    //根据id查询
    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    //修改密码
    @Override
    public Result updatePassword(User user, HttpServletRequest request) {
        //获取当前登录用户信息
        Claims claims = JwtUtils.getData(request);
        int currentUserLevel = (Integer) claims.get("level");
        String username = (String) claims.get("username");
        String password = (String) claims.get("password");
        int id = (Integer) claims.get("id");
        //在数据库中查询到该用户
        User u = userMapper.getById(id);

        if(currentUserLevel == 0){//管理员
            userMapper.updatePassword(user);
            return Result.success();
        }else if(u.getPassword().equals(password) && u.getUsername().equals(username)){//当前登录用户的信息和根据id从数据库中查询到的用户信息一致
            userMapper.updatePassword(user);
            return Result.success();
        }else {
            return Result.error("权限不足！");
        }
    }
}
