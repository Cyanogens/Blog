package com.turingcourt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turingcourt.dao.BlogCategoryDao;
import com.turingcourt.dao.BlogDao;
import com.turingcourt.dao.CategoryDao;
import com.turingcourt.dao.UserDao;
import com.turingcourt.entity.Blog;
import com.turingcourt.entity.BlogCategory;
import com.turingcourt.entity.Category;
import com.turingcourt.entity.User;
import com.turingcourt.service.BlogService;
import com.turingcourt.utils.ToVO;
import com.turingcourt.vo.BlogVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private BlogCategoryDao blogCategoryDao;

    @Resource
    private ToVO to;

    /**
     * 先访问之后加一，然后再得到实体类，进行查找
     *
     * @param blogId 博客id
     * @return 浏览量
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
     * @param pageSize 一页显示多少条
     * @return 随机博客列表
     */
    @Override
    public PageInfo<BlogVO> blogRandomList(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Blog> blogs = blogDao.queryRandomBlog();

        List<BlogVO> blogVOS = new ArrayList<>();
        for (Blog blog : blogs) {
            blogVOS.add(to.blogToVO(blog));
        }
        return new PageInfo<>(blogVOS);
    }

    /**
     * 展示blog
     *
     * @param blogId 博客id
     * @return 博客内容
     */
    @Override
    public BlogVO blogDetail(Long blogId) {
        Blog blog = blogDao.getBlog(blogId);
        return to.blogToVO(blog);
    }

    @Override
    public Long insertBlog(BlogVO blogVO) {
        System.out.println("blogVO = " + blogVO);
        User user = userDao.getUserByName(blogVO.getUserName());
        Blog blog = Blog.builder()
                .title(blogVO.getTitle()).mdContent(blogVO.getMdContent())
                .htmlContent(blogVO.getHtmlContent()).summary(blogVO.getSummary())
                .state(true).uid(user.getId()).publishData(new Date())
                .pageView(0L).likeCount(0L)
                .build();
        blogDao.insertBlog(blog);
        Long blogId = blog.getId();

        //标签名集合去重处理
        List<String> categoryList = blogVO.getCategoryNames().stream().distinct().collect(Collectors.toList());
        //对发布的博客的标签集进行处理
        for (String categoryName : categoryList) {
            //先判断标签表里是否存在
            Category category = categoryDao.queryCategoryByName(categoryName);
            if (category == null) {
                category = new Category(null, categoryName);
                categoryDao.insertCategory(category);
            }
            blogCategoryDao.insert(new BlogCategory(null, blogId, category.getId()));
        }
        return blogId;
    }

    @Override
    public PageInfo<BlogVO> searchBlog(String key, int pageNo, int pageSize) {
        int byTitle = pageSize / 2;
        int byCategory = pageSize - byTitle;

        key = "%" + key + "%";
        List<Blog> blogs = new ArrayList<>();

        PageHelper.startPage(pageNo, byCategory);
        List<Blog> blogs1 = blogDao.queryByCategory(key);
        PageHelper.startPage(pageNo, byTitle);
        List<Blog> blogs2 = blogDao.queryByTitle(key);

        blogs.addAll(blogs1);
        blogs.addAll(blogs2);

        //去重
        blogs = blogs.stream().distinct().collect(Collectors.toList());
        Collections.shuffle(blogs);

        List<BlogVO> blogVOS = new ArrayList<>();
        for (Blog blog : blogs) {
            blogVOS.add(to.blogToVO(blog));
        }
        return new PageInfo<>(blogVOS);
    }
}

