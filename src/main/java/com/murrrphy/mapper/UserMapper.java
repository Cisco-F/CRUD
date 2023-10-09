package com.murrrphy.mapper;

import com.murrrphy.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    //登录
    @Select("select * from user where username = #{username} and password = #{password}")
    User login(User user);

    //注册
    @Insert("insert into user(username, password, level)" +
            "values (#{username}, #{password}, #{level})")
    void register(User user);

    //注销
    @Delete("delete from user where id = #{id}")
    void logOff(Integer id);

    //查询所有用户
    @Select("select * from user")
    List<User> list();

    //根据id查询用户
    @Select("select * from user where id = #{id}")
    User getById(Integer id);

    //修改密码
    @Update("update user set password = #{password} where id = #{id}")
    void updatePassword(User user);

    //根据用户名查询
    @Select("select * from user where username = #{username}")
    User getByUsername(User user);
}
