package com.turingcourt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Cyanogen
 * @description Comment类视图层
 * @date 2022/3/8 20:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {

    /**
     * 评论id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
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
