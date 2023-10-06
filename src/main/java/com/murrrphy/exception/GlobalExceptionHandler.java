package com.murrrphy.exception;

import com.murrrphy.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//异常处理类
    public Result ex(Exception e){
        e.printStackTrace();
        return Result.error("操作失败！");
    }
}
