package com.murrrphy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 储存用户登录信息和加密等级
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;//唯一标识
    private String username;//用户名
    private String password;//密码
    private Integer level;//加密等级，1对应阿摆，2对应阿卷
}
