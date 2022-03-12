package com.turingcourt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * 博客内容表(Blog)实体类
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog implements Serializable {
    private static final long serialVersionUID = 538705558850997085L;

    private Long id;
    /**
    * 标题
    */
    private String title;
    /**
    * md格式的内容
    */
    private String mdContent;
    /**
    * html格式的内容
    */
    private String htmlContent;
    /**
    * 内容
    */
    private String summary;
    /**
    * 标签id
    */
    private Long cid;
    /**
    * 用户id
    */
    private Integer uid;
    /**
    * 发布时间
    */
    private Date publishData;
    /**
    * 状态
    */
    private Boolean state;
    /**
    * 浏览量
    */
    private Long pageView;
    /**
    * 点赞数
    */
    private Long likeCount;

}
