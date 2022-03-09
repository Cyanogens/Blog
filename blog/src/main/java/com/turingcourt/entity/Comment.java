package com.turingcourt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    private static final long serialVersionUID = 130373938204497991L;

    private Long id;
    /**
    * 所评论的id
    */
    private Long pid;
    /**
    * 博客id
    */
    private Long bid;
    /**
    * 评论者id
    */
    private Integer uid;
    /**
    * 评论内容
    */
    private String content;
    /**
    * 发布时间
    */
    private Date createDate;
    /**
    * 点赞数
    */
    private Long likeCount;

}
