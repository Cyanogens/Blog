package com.turingcourt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (CommentLikes)实体类
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentLikes implements Serializable {
    private static final long serialVersionUID = 560964061898203008L;

    private Long id;
    /**
    * 评论id
    */
    private Long cid;
    /**
    * 用户id
    */
    private Integer uid;

}
