package com.murrrphy.mapper;

import com.murrrphy.pojo.Post;
import com.murrrphy.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("select * from user where username = #{username} and password = #{password}")
    User login(User user);

    @Select("select * from my_posts")
    List<Post> list();

    void delete(List<Integer> ids);

    //添加文章
    @Insert("insert into my_posts(title, author, post_content, create_time, update_time)" +
            "values (#{title}, #{author}, #{postContent}, #{createTime}, #{updateTime})")
    void add(Post post);

    @Select("select * from my_posts where id = #{id}")
    Post getById(Integer id);

    void update(Post post);

}
