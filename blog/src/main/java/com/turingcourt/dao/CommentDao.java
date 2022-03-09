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
     * 封装成comment对象
     *
     * @param comment 实例对象
     * @return 对象列表
     */
    List<Comment> queryAll(Comment comment);

    /**
     * 发布评论/回复
     *
     * @param comment 评论内容/回复
     * @return 影响行数
     */
    int insert(Comment comment);

    /**
     * 删除评论
     * 也要删除该评论下的回复
     *
     * @param id 评论id
     * @return 影响行数
     */
    int deleteComment(Long id);

}
