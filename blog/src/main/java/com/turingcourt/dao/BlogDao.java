package com.turingcourt.dao;

import com.turingcourt.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 博客内容表(Blog)表数据库访问层
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:51
 */
@Mapper
public interface BlogDao {

    /**
     * 查询博客详情
     *
     * @param blogId 主键
     * @return 实例对象
     */
    Blog getBlog(Long blogId);

    /**
     * 查询用户发布的博客列表
     *
     * @param blog 实例对象
     * @return 对象列表
     */
    List<Blog> queryAll(Blog blog);

    /**
     * 发布博客
     *
     * @param blog 实例对象
     * @return 影响行数
     */
    int insertBlog(Blog blog);

    /**
     * 更改用户发布的博客
     *
     * @param blog 新博客内容
     * @return 影响行数
     */
    int updateBlog(Blog blog);

    /**
     * 删除博客
     *
     * @param id 博客id
     * @return 影响行数
     */
    int deleteBlog(Long id);

    /**
     * 获取随机博客列表
     *
     * @return 随机博客集合
     */
    List<Blog> queryRandomBlog();

    /**
     * 更改博客浏览量
     *
     * @param blogId 博客id
     * @return 影响行数
     */
    int changeView(Long blogId);

    /**
     * 根据标题搜索博客
     *
     * @param key 搜索的标题
     * @return 博客集合
     */
    List<Blog> queryByTitle(String key);

    /**
     * 根据标签搜索博客
     *
     * @param key 搜索的标签
     * @return 博客集合
     */
    List<Blog> queryByCategory(String key);

}
