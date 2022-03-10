package com.turingcourt.service;

import com.github.pagehelper.PageInfo;
import com.turingcourt.entity.Comment;
import com.turingcourt.vo.CommentVO;

/**
 * (Comment)表服务接口
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
public interface CommentService {

    /**
     * 查询某博客的所有评论
     *
     * @param blogId   博客id
     * @param pageNo   要显示第几页内容
     * @param pageSize 一页显示多少条
     * @return 所有评论
     */
    PageInfo<Comment> getAllComment(Long blogId, int pageNo, int pageSize);

    /**
     * 发布评论
     * 评论完要重新把该博客的所有评论查一遍
     *
     * @param commentVO 评论内容
     * @param blogId    评论的博客id
     * @return 是否发步成功
     */
    Boolean insertComment(CommentVO commentVO, Long blogId);

    /**
     * 回复评论
     *
     * @param commentVO 回复内容
     * @param pid       评论的id
     * @return 是否回复成功
     */
    Boolean replyComment(CommentVO commentVO, Long pid);

    /**
     * 删除评论
     *
     * @param id 评论的id
     * @return 是否删除成功
     */
    Boolean deleteComment(Long id);
}
