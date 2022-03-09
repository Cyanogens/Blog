package com.turingcourt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
     * 标签名
     */
    private String categoryName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 发布时间
     */
    private Date publishData;

}
