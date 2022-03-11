package com.turingcourt.service;

import com.github.pagehelper.PageInfo;
import com.turingcourt.entity.Blog;
import com.turingcourt.vo.BlogVO;

/**
 * 博客内容表(Blog)表服务接口
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:51
 */
public interface BlogService {

    /**
     * 博客浏览量增加
     *
     * @param blogId 博客id
     * @return 浏览量
     */
    Long viewBlog(Long blogId);

    /**
     * 获得随机博客列表
     *
     * @param pageNo   要显示第几页内容
     * @param pageSize 一页显示多少条
     * @return 随机博客列表
     */
    PageInfo<BlogVO> blogRandomList(int pageNo, int pageSize);

    /**
     * 查看博客详情
     *
     * @param blogId 博客id
     * @return 博客内容
     */
    BlogVO blogDetail(Long blogId);

    /**
     * 发布博客
     *
     * @param blogVO 博客内容
     * @return 博客id
     */
    Long insertBlog(BlogVO blogVO);

    /**
     * 搜索博客/标签
     *
     * @param key      搜索关键字
     * @param pageNo   要显示第几页内容
     * @param pageSize 一页显示多少条
     * @return 博客集合
     */
    PageInfo<BlogVO> searchBlog(String key, int pageNo, int pageSize);

}
