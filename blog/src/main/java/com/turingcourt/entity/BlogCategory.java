package com.turingcourt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (BlogCategory)实体类
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCategory implements Serializable {
    private static final long serialVersionUID = -70419360253655972L;

    private Long id;
    /**
    * 博客id
    */
    private Long bid;
    /**
    * 标签id
    */
    private Long cid;
}
