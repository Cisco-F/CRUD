package com.murrrphy.mapper;

import com.murrrphy.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //登录
    @Select("select * from user where username = #{username} and password = #{password}")
    User login(User user);

    //注册
    @Insert("insert into user(username, password, level)" +
            "values (#{username}, #{password}, #{level})")
    void register(User user);
}
