package com.murrrphy.conrtoller;

import com.murrrphy.pojo.Result;
import com.murrrphy.pojo.User;
import com.murrrphy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.murrrphy.utils.JwtUtils.generateJwt;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    //登录功能
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("用户登录：{}", user);
        User u = userService.login(user);

        //登录成功，生成并下发令牌
        if(u != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", u.getUsername());
            claims.put("password", u.getPassword());
            claims.put("level", u.getLevel());
            String jwt = generateJwt(claims);
            return Result.success(jwt);
        }

        //登录失败
        return  Result.error("用户名或密码错误！");
    }

    //注册功能
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        //输出日志
        log.info("注册用户：{}", user);
        //调用service层
        userService.register(user);
        return Result.success();
    }
}
