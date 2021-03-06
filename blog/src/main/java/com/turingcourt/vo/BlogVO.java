package com.turingcourt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * @author Cyanogen
 * @description 发布Blog的视图层
 * @date 2022/3/8 20:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogVO {

    /**
     * 博客id
     */
    private Long id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不为空")
    private String title;

    /**
     * md格式的内容
     */
    @NotBlank(message = "")
    private String mdContent;

    /**
     * html格式的内容
     */
    @NotBlank(message = "")
    private String htmlContent;

    /**
     * 内容
     */
    private String summary;

    /**
     * 标签名集合
     */
    @NotEmpty(message = "")
    private List<String> categoryNames;

    /**
     * 用户名
     */
    @NotBlank(message = "")
    private String userName;

    /**
     * 发布时间
     */
    private Date publishData;

    /**
     * 点赞数
     */
    private Long likeCount;

    /**
     * 浏览量
     */
    private Long pageView;

    /**
     * 用户是否点赞
     * true为已点赞
     * false为未点赞
     */
    private Boolean isLiked;
}
