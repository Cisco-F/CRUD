package com.murrrphy.conrtoller;

import com.murrrphy.pojo.Result;
import com.murrrphy.pojo.User;
import com.murrrphy.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.murrrphy.utils.JwtUtils.generateJwt;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private PostService postService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("用户登录：{}", user);
        User u = postService.login(user);

        //登录成功，生成并下发令牌
        if(u != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", u.getUsername());
            claims.put("password", u.getPassword());
            String jwt = generateJwt(claims);
            return Result.success(jwt);
        }

        //登录失败
        return  Result.error("用户名或密码错误！");
    }
}
