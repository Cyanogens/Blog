package com.turingcourt.utils;

import com.turingcourt.dao.BlogLikesDao;
import com.turingcourt.dao.CategoryDao;
import com.turingcourt.dao.CommentLikesDao;
import com.turingcourt.dao.UserDao;
import com.turingcourt.entity.*;
import com.turingcourt.vo.BlogVO;
import com.turingcourt.vo.CommentVO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Cyanogen
 * @description BlogEntity -> BlogVO
 * @date 2022/3/16 17:23
 */
@Component
public class ToVO {

    @Resource
    CategoryDao categoryDao;

    @Resource
    UserDao userDao;

    @Resource
    BlogLikesDao blogLikesDao;

    @Resource
    CommentLikesDao commentLikesDao;

    public BlogVO blogToVO(Blog blog) {

        //判断当前登录用户是否点赞该博客
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean isLiked = false;
        //未登录时,principal 为字符串
        //所以要用户登录后才强转
        if (!"anonymousUser".equals(principal)) {
            User loggedInUser = (User) principal;
            Integer userId = loggedInUser.getId();
            isLiked = blogLikesDao.queryLike(new BlogLikes(null, blog.getId(), userId));
        }
        User user = userDao.getUser(blog.getUid());

        List<String> categoryNames = new ArrayList<>();
        List<Category> categories = categoryDao.queryByBlogId(blog.getId());
        for (Category category : categories) {
            categoryNames.add(category.getCategoryName());
        }

        return new BlogVO(blog.getId(), blog.getTitle(), blog.getMdContent(), blog.getHtmlContent(),
                blog.getSummary(), categoryNames, user.getUsername(), blog.getPublishData(),
                blog.getLikeCount(), blog.getPageView(), isLiked);
    }

    public CommentVO commentToVO(Comment comment) {

        //判断当前登录用户是否点赞该评论
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean isLiked = false;
        //未登录时,principal 为字符串
        //所以要用户登录后才强转
        if (!"anonymousUser".equals(principal)) {
            User loggedInUser = (User) principal;
            Integer userId = loggedInUser.getId();
            isLiked = commentLikesDao.queryLike(new CommentLikes(null, comment.getId(), userId));
        }

        User user = userDao.getUser(comment.getUid());

        return new CommentVO(comment.getId(), user.getUsername(),
                comment.getContent(), comment.getCreateDate(),
                comment.getLikeCount(), isLiked);
    }
}
