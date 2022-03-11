package com.turingcourt.service.impl;

import com.github.pagehelper.PageInfo;
import com.turingcourt.dao.UserDao;
import com.turingcourt.entity.Blog;
import com.turingcourt.entity.User;
import com.turingcourt.service.UserService;
import com.turingcourt.vo.BlogVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public User getUser(Integer userId) {
        return null;
    }

    @Override
    public User changeUser(User user) {
        return null;
    }

    @Override
    public PageInfo<BlogVO> userBlogs(Integer userId, int pageNo, int pageSize) {
        return null;
    }

    @Override
    public Boolean changeUserBlog(BlogVO blogVO, Long blogId) {
        return null;
    }

    @Override
    public Boolean deleteUserBlog(Long blogId) {
        return null;
    }
}
