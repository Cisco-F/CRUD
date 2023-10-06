package com.murrrphy.conrtoller;

import com.murrrphy.pojo.Result;
import com.murrrphy.pojo.User;
import com.murrrphy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    //注销功能
    @DeleteMapping("/{id}")
    public Result logOff(@PathVariable Integer id, HttpServletRequest request){
        //输出日志
        log.info("注销用户：{}", id);
        //调用service层
        return userService.logOff(id, request);
    }

    //查看所有用户（只有管理员有权限）
    @GetMapping
    public Result list(HttpServletRequest request){
        //输出日志
        log.info("查询所有用户");
        //调用service层
        return userService.list(request);
    }

    //根据id查询用户
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        //输出日志
        log.info("查询用户：{}", id);
        //调用service层
        User user = userService.getById(id);
        return Result.success(user);
    }

    //修改密码
    @PutMapping
    public Result updatePassword(@RequestBody User user, HttpServletRequest request){
        //输出日志
        log.info("修改密码，用户：{}", user);
        //调用service层
        return userService.updatePassword(user, request);
    }

}
