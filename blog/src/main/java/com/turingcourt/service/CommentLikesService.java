package com.turingcourt.service;

/**
 * (CommentLikes)表服务接口
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
public interface CommentLikesService {
    /**
     * 对评论进行点赞
     * 如果该用户未点赞,则点赞
     * 如果该用户已点赞,则取消点赞
     *
     * @param commentId 博客id
     * @param userId    用户id
     * @return 该用户是否点赞
     */
    Boolean likeComment(Long commentId, Integer userId);

}
