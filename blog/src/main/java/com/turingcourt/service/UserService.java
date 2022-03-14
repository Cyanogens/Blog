package com.turingcourt.service;

import com.github.pagehelper.PageInfo;
import com.turingcourt.entity.User;
import com.turingcourt.vo.BlogVO;

/**
 * (User)表服务接口
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
public interface UserService {

    /**
     * 查询用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    User getUser(Integer userId);

    /**
     * 更改用户信息
     *
     * @param user 更新后的用户信息
     * @return 用户信息
     */
    User changeUser(User user);

    /**
     * 查询用户发布的全部博客
     * 通过用户id查找博客
     *
     * @param userId   用户id
     * @param pageNo   要显示第几页内容
     * @param pageSize 一页显示多少条
     * @return 博客列表
     */
    PageInfo<BlogVO> userBlogs(Integer userId, int pageNo, int pageSize);


    /**
     * 更改用户发布的博客
     *
     * @param blogId 博客id
     * @param blogVO 更新后的博客
     * @return 是否更新成功
     */
    Boolean changeUserBlog(BlogVO blogVO, Long blogId,Integer uid);

    /**
     * 删除用户发布的博客
     * 博客下所有评论也要删除
     * 博客下所有标签关系也要删除
     * 博客下所有点赞关系也要删除
     *
     * @param blogId 博客id
     * @return 是否删除成功
     */
    Boolean deleteUserBlog(Long blogId);

}
