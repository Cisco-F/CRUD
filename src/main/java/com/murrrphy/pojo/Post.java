package com.murrrphy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 储存文章相关信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer id;//唯一标识
    private String title;//标题
    private String author;//作者
    private String postContent;//文章内容
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//修改时间
}
