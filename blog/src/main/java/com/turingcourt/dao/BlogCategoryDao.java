package com.turingcourt.dao;

import com.turingcourt.entity.BlogCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * (BlogCategory)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-14 10:12:03
 */
@Mapper
public interface BlogCategoryDao {

    /**
     * 新增博客-标签关系
     *
     * @param blogCategory 复合对象
     * @return 影响行数
     */
    int insert(BlogCategory blogCategory);

    /**
     * 通过博客id删除关系
     *
     * @param blogId 主键
     * @return 影响行数
     */
    int deleteByBlogId(Long blogId);

    /**
     * 通过标签id删除关系
     *
     * @param categoryId 主键
     * @return 影响行数
     */
    int deleteByCategoryId(Long categoryId);

}
