package com.turingcourt.utils;

import com.turingcourt.dao.CategoryDao;
import com.turingcourt.dao.UserDao;
import com.turingcourt.entity.Blog;
import com.turingcourt.entity.Category;
import com.turingcourt.entity.User;
import com.turingcourt.vo.BlogVO;
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
public class BlogToVO {

    @Resource
    CategoryDao categoryDao;

    @Resource
    UserDao userDao;

    public BlogVO blogToVO(Blog blog) {

        User user = userDao.getUser(blog.getUid());

        List<String> categoryNames = new ArrayList<>();
        List<Category> categories = categoryDao.queryByBlogId(blog.getId());
        for (Category category : categories) {
            categoryNames.add(category.getCategoryName());
        }

        return new BlogVO(blog.getId(), blog.getTitle(), blog.getMdContent(), blog.getHtmlContent(),
                blog.getSummary(), categoryNames, user.getUsername(), blog.getPublishData(),
                blog.getLikeCount(), blog.getPageView());
    }
}
