package com.murrrphy.conrtoller;

import com.murrrphy.pojo.Post;
import com.murrrphy.pojo.Result;
import com.murrrphy.service.PostService;
import com.murrrphy.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j//控制台输出日志
@RestController//控制层
@RequestMapping("/posts")//请求路径
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    //查询所有文章
    public Result list(HttpServletRequest request){
        //输出日志
        log.info("查询所有文章");
        //调用service层，使用集合接收查询到的数据
        List<Post> postList = postService.list(request);
        return Result.success(postList);
    }

    //批量删除文章
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        //输出日志
        log.info("根据id删除文章：{}", ids);
        //调用service层
        postService.delete(ids);
        return Result.success();//不需要返回数据
    }

    //添加文章
    @PostMapping
    public Result add(@RequestBody Post post){
        //输出日志
        log.info("添加文章");
        //调用service层
        postService.add(post);
        return Result.success();
    }

    //根据id查询文章，用于修改或删除操作之前的回显
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        //输出日志
        log.info("查询id为{}的文章", id);
        //调用service层
        Post post = postService.getById(id);//查询到的数据封装到实例对象
        return Result.success(post);
    }

    //修改文章
    @PutMapping
    public Result update(@RequestBody Post post){
        //输出日志
        log.info("修改文章：{}", post.getId());
        //调用service层修改updateTime
        postService.update(post);
        return Result.success();
    }
}
