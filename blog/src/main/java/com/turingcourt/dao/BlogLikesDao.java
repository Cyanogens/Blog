package com.turingcourt.dao;

import com.turingcourt.entity.BlogLikes;
import org.apache.ibatis.annotations.Mapper;

/**
 * (BlogLikes)表数据库访问层
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Mapper
public interface BlogLikesDao {

    /**
     * 查询博客是否点赞
     *
     * @param blogLikes 评论id与用户id的复合对象
     * @return 是否点赞的Boolean值
     */
    Boolean queryLike(BlogLikes blogLikes);

    /**
     * 点赞博客
     *
     * @param blogLikes 博客id与用户id的复合对象
     * @return 影响行数
     */
    int likeBlog(BlogLikes blogLikes);

    /**
     * 取消点赞
     *
     * @param blogLikes 博客id与用户id的复合对象
     * @return 影响行数
     */
    int unlikeBlog(BlogLikes blogLikes);

    /**
     * 通过博客id删除博客点赞
     * @param bid
     * @return
     */
    int deleteByBid( Long bid);

}
