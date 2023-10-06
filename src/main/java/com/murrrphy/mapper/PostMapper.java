package com.murrrphy.mapper;

import com.murrrphy.pojo.Post;
import com.murrrphy.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {
    //查询所有文章
    @Select("select * from my_posts where level >= #{level} order by update_time desc")
    List<Post> list(Integer level);

    //（批量）删除
    void delete(List<Integer> ids);

    //添加文章
    @Insert("insert into my_posts(title, id, author, post_content, create_time, update_time)" +
            "values (#{title}, #{author}, #{id}, #{postContent}, #{createTime}, #{updateTime})")
    void add(Post post);

    //根据id查询
    @Select("select * from my_posts where id = #{id}")
    Post getById(Integer id);

    //更新文章
    void update(Post post);

    //注册用户
    @Insert("insert into user(username, password, level)" +
            "values (#{username}, #{password}, #{level})")
    void register(User user);
}
