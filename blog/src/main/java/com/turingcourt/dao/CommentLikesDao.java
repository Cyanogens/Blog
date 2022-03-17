package com.turingcourt.dao;

import com.turingcourt.entity.CommentLikes;
import org.apache.ibatis.annotations.Mapper;

/**
 * (CommentLikes)表数据库访问层
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Mapper
public interface CommentLikesDao {

    /**
     * 查询评论是否点赞
     *
     * @param commentLikes 评论id与用户id的复合对象
     * @return 是否点赞的Boolean值
     */
    Boolean queryLike(CommentLikes commentLikes);

    /**
     * 对评论点赞
     *
     * @param commentLikes 评论id与用户id的复合对象
     * @return 影响行数
     */
    int likeComment(CommentLikes commentLikes);

    /**
     * 取消点赞
     *
     * @param commentLikes 评论id与用户id的复合对象
     * @return 影响行数
     */
    int unlikeComment(CommentLikes commentLikes);


}
