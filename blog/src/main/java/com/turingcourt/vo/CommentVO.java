package com.turingcourt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "")
    private String username;

    /**
     * 评论内容
     */
    @NotBlank(message = "")
    private String content;

    /**
     * 发布时间
     */
    private Date createDate;
    /**
     * 点赞数
     */
    private Long likeCount;
    /**
     * 是否点赞
     * true为已点赞
     * false为未点赞
     */
    private Boolean isLiked;
}
