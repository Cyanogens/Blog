package com.turingcourt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (BlogLikes)实体类
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogLikes implements Serializable {
    private static final long serialVersionUID = -16899743832289833L;

    private Long id;
    /**
    * 博客id
    */
    private Long bid;
    /**
    * 用户id
    */
    private Integer uid;

}
