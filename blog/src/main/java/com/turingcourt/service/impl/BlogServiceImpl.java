package com.turingcourt.service.impl;

import com.github.pagehelper.PageInfo;
import com.turingcourt.dao.BlogDao;
import com.turingcourt.service.BlogService;
import com.turingcourt.vo.BlogVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 博客内容表(Blog)表服务实现类
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:52
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogDao blogDao;


    @Override
    public Long viewBlog(Long blogId) {
        return null;
    }

    @Override
    public PageInfo<BlogVO> blogRandomList(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public BlogVO blogDetail(Long blogId) {
        return null;
    }

    @Override
    public Long insertBlog(BlogVO blogVO) {
        return null;
    }

    @Override
    public PageInfo<BlogVO> searchBlog(String key, int pageNo, int pageSize) {
        return null;
    }
}
