package com.turingcourt.service;

/**
 * (BlogLikes)表服务接口
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
public interface BlogLikesService {

    /**
     * 对博客进行点赞
     * 如果该用户未点赞,则点赞
     * 如果该用户已点赞,则取消点赞
     *
     * @param blogId 博客id
     * @param userId 用户id
     * @return 该用户是否点赞
     */
    Boolean likeBlog(Long blogId, Integer userId);

}
