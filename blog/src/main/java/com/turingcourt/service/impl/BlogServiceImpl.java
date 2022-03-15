package com.turingcourt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turingcourt.dao.BlogDao;
import com.turingcourt.dao.CategoryDao;
import com.turingcourt.dao.UserDao;
import com.turingcourt.entity.Blog;
import com.turingcourt.entity.Category;
import com.turingcourt.entity.User;
import com.turingcourt.service.BlogService;
import com.turingcourt.vo.BlogVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    /**
     * 注入UserDao
     */
    @Resource
    private UserDao userDao;

    @Resource
    private CategoryDao categoryDao;

    /**
     * 先访问之后加一，然后再得到实体类，进行查找
     * @param blogId 博客id
     * @return
     */
    @Override
    public Long viewBlog(Long blogId) {
        blogDao.changeView(blogId);
        Blog blog = blogDao.getBlog(blogId);
        return blog.getPageView();
    }

    /**
     * 得到随机排列的blog集合，并且分页展示，返回的实体类为BlogVO
     *
     * @param pageNo   要显示第几页内容
     * @para pageSize 一页显示多少条
     * @return
     */
    @Override
    public PageInfo<BlogVO> blogRandomList(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Blog> blogs = blogDao.queryRandomBlog();
        List<BlogVO> blogVOS = new ArrayList<>();
        for(Blog blog : blogs){
            List<Category> categories = categoryDao.queryByBlogId(blog.getId());
            List<String> categoryNames = new ArrayList<>();
            User user = userDao.getUser(blog.getUid());
            for(Category category : categories){
                categoryNames.add(category.getCategoryName());
            }
            blogVOS.add(new BlogVO(blog.getId(), blog.getTitle(), blog.getMdContent(), blog.getHtmlContent(),
                    blog.getSummary(), categoryNames, user.getUsername(), blog.getPublishData(),
                    blog.getLikeCount(), blog.getPageView()));
        }
        PageInfo<BlogVO> pageInfo = new PageInfo<>(blogVOS);
        return pageInfo;
    }

    /**
     * 展示blog
     *
     * @param blogId 博客id
     * @return
     */
    @Override
    public BlogVO blogDetail(Long blogId) {
        Blog blog = blogDao.getBlog(blogId);
        List<Category> categories = categoryDao.queryByBlogId(blog.getId());
        List<String> categoryNames = new ArrayList<>();
        User user = userDao.getUser(blog.getUid());
        for(Category category : categories){
            categoryNames.add(category.getCategoryName());
        }
        return new BlogVO(blog.getId(), blog.getTitle(), blog.getMdContent(), blog.getHtmlContent(),
                blog.getSummary(), categoryNames, user.getUsername(), blog.getPublishData(),
                blog.getLikeCount(), blog.getPageView());
    }

    /**
     * 此处有过更改 long -> int
     *
     * @param blogVO 博客内容
     * @return
     */
    @Override
    public Long insertBlog(BlogVO blogVO) {
        User user = userDao.getUserByName(blogVO.getUserName());
        Blog blog = new Blog();
        blog.setTitle(blogVO.getTitle());
        blog.setMdContent(blogVO.getMdContent());
        blog.setHtmlContent(blogVO.getHtmlContent());
        blog.setSummary(blogVO.getSummary());
        blog.setState(true);
        blog.setUid(user.getId());
        blog.setPublishData(blogVO.getPublishData());
        blog.setPageView(blogVO.getPageView());
        blog.setLikeCount(blogVO.getLikeCount());
        blogDao.insertBlog(blog);
        return blog.getId();
    }

    @Override
    public PageInfo<BlogVO> searchBlog(String key, int pageNo, int pageSize) {
        List<Blog> blogs = blogDao.queryByCategory(key);
        PageHelper.startPage(pageNo, pageSize);
        List<BlogVO> blogVOS = new ArrayList<>();
        for(Blog blog : blogs){
            List<Category> categories = categoryDao.queryByBlogId(blog.getId());
            List<String> categoryNames = new ArrayList<>();
            User user = userDao.getUser(blog.getUid());
            for(Category category : categories){
                categoryNames.add(category.getCategoryName());
            }
            blogVOS.add(new BlogVO(blog.getId(), blog.getTitle(), blog.getMdContent(), blog.getHtmlContent(),
                    blog.getSummary(), categoryNames, user.getUsername(), blog.getPublishData(),
                    blog.getLikeCount(), blog.getPageView()));
        }
        PageInfo<BlogVO> pageInfo = new PageInfo<>(blogVOS);
        return pageInfo;
    }
}

