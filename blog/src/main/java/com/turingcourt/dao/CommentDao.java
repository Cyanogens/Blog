package com.turingcourt.dao;

import com.turingcourt.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Comment)表数据库访问层
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Mapper
public interface CommentDao {

    /**
     * 根据博客id查询评论
     *
     * @param blogId 实例对象
     * @return 对象列表
     */
    List<Comment> queryComment(Long blogId);

    /**
     * 查询单条评论
     *
     * @param commentId 评论id
     * @return 评论类
     */
    Comment getComment(Long commentId);

    /**
     * 发布评论/回复
     *
     * @param comment 评论内容/回复
     * @return 影响行数
     */
    int insert(Comment comment);

    /**
     * 删除评论
     *
     * @param commentId 评论id
     * @return 影响行数
     */
    int deleteComment(Long commentId);

    /**
     * 根据博客id删除评论
     *
     * @param bid 博客id
     * @return 影响行数
     */
    int deleteCommentByBid(Long bid);

    /**
     * 删除评论的子评论
     *
     * @param tree 树型
     * @return 影响行数
     */
    int deleteChildComment(String tree);

}
