package com.turingcourt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turingcourt.dao.*;
import com.turingcourt.entity.Blog;
import com.turingcourt.entity.BlogCategory;
import com.turingcourt.entity.Category;
import com.turingcourt.entity.User;
import com.turingcourt.service.UserService;
import com.turingcourt.utils.BeanCopyUtils;
import com.turingcourt.utils.ToVO;
import com.turingcourt.vo.BlogVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (User)表服务实现类
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private BlogDao blogDao;
    @Resource
    private CommentDao commentDao;
    @Resource
    private BlogCategoryDao blogCategoryDao;
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private BlogLikesDao blogLikesDao;
    @Resource
    private ToVO to;

    @Override
    public User getUser(Integer userId) {
        return userDao.getUser(userId);
    }

    @Override
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    @Override
    public User changeUser(User user) {
        if (userDao.update(user) >= 1) {
            return user;
        } else {
            return null;
        }

    }

    /**
     * 查询用户发布的全部博客
     * 通过用户id查找博客
     *
     * @param userId   用户id
     * @param pageNo   要显示第几页内容
     * @param pageSize 一页显示多少条
     * @return 博客列表
     */
    @Override
    public PageInfo<BlogVO> userBlogs(Integer userId, int pageNo, int pageSize) {

        PageHelper.startPage(pageNo, pageSize);
        List<Blog> blogs = blogDao.queryByUserId(userId);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        System.out.println("blogs = " + blogs);

        List<BlogVO> blogVOS = new ArrayList<>();
        for (Blog blog : blogs) {
            blogVOS.add(to.blogToVO(blog));
        }
        //要进行复制,否则会有total等于pageSize的问题
        PageInfo<BlogVO> res = BeanCopyUtils.copyBean(pageInfo, PageInfo.class);
        res.setList(blogVOS);
        return res;
    }

    /**
     * 更改用户发布的博客
     *
     * @param blogId 博客id
     * @param blogVO 更新后的博客
     * @return 是否更新成功
     */
    @Override
    public Boolean changeUserBlog(BlogVO blogVO, Long blogId) {
        Integer uid = blogDao.getBlog(blogId).getUid();
        Blog blog = new Blog(blogId, blogVO.getTitle(), blogVO.getMdContent(),
                blogVO.getHtmlContent(), blogVO.getSummary(), uid, blogVO.getPublishData(),
                true, blogVO.getPageView(), blogVO.getLikeCount());
        int update = blogDao.updateBlog(blog);
        //删除原来所有的博客-标签关系
        blogCategoryDao.deleteByBlogId(blogId);

        //重新建立博客-标签关系
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

        return update > 0;
    }

    /**
     * 删除用户发布的博客
     * 博客下所有评论也要删除
     * 博客下所有标签关系也要删除
     * 博客下所有点赞关系也要删除
     *
     * @param blogId 博客id
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteUserBlog(Long blogId) {
        //删除博客
        int blog = blogDao.deleteBlog(blogId);
        //根据博客id删除博客下的评论
        int comment = commentDao.deleteCommentByBid(blogId);
        //根据博客id删除标签下的博客
        int category = blogCategoryDao.deleteByBlogId(blogId);
        //根据博客id删除博客下的给博客的点赞
        int like = blogLikesDao.deleteByBid(blogId);
        return blog > 0 && comment > 0 && category > 0 && like > 0;

    }
}
