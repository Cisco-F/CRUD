package com.murrrphy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应数据统一格式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;//响应码 0失败 1成功
    private String msg;//相应信息
    private Object data;//返回数据

    //创建相关的静态方法，提高程序运行效率
    //增删改 响应成功
    public static Result success(){
        return new Result(1, "success", null);
    }

    //查询 相应成功
    public static Result success(Object data){
        return new Result(1, "success", data);
    }

    //相应失败
    public static Result error(String msg){
        return new Result(0, msg, null);
    }
}
