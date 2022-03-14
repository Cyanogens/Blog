package com.turingcourt.dao;

import com.turingcourt.entity.BlogCategory;

/**
 * (BlogCategory)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-14 10:12:03
 */
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
    int deleteById(Long blogId);

}
