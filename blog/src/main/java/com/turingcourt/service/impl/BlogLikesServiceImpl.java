package com.turingcourt.service.impl;

import com.turingcourt.dao.BlogLikesDao;
import com.turingcourt.entity.BlogLikes;
import com.turingcourt.service.BlogLikesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (BlogLikes)表服务实现类
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Service("blogLikesService")
public class BlogLikesServiceImpl implements BlogLikesService {
    @Resource
    private BlogLikesDao blogLikesDao;

    @Override
    public Boolean likeBlog(Long blogId, Integer userId) {
        Boolean aBoolean = blogLikesDao.queryLike(new BlogLikes(null, blogId, userId));
        if (aBoolean) {
            blogLikesDao.unlikeBlog(new BlogLikes(null, blogId, userId));
            return false;
            } else {
            blogLikesDao.likeBlog(new BlogLikes(null, blogId, userId));
            return true;
        }
    }
}
