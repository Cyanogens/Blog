package com.turingcourt.controller;

import com.turingcourt.config.json.JsonResult;
import com.turingcourt.config.json.ResultTool;
import com.turingcourt.service.BlogLikesService;
import com.turingcourt.service.CommentLikesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 点赞
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@CrossOrigin
@RestController
@ApiOperation("点赞操作")
@RequestMapping("/like")
public class LikesController {

    @Autowired
    private BlogLikesService blogLikesService;
    @Autowired
    private CommentLikesService commentLikesService;

    /**
     * 对博客进行点赞
     * 如果该用户未点赞,则点赞
     * 如果该用户已点赞,则取消点赞
     *
     * @param blogId 博客id
     * @param userId 用户id
     * @return 该用户是否更改点赞状态
     */
    @PostMapping("/blog")
    @ApiOperation("对博客进行点赞")
    public JsonResult likeBlog(Long blogId, Integer userId) {
        Boolean like = blogLikesService.likeBlog(blogId, userId);
        return like ? ResultTool.success() : ResultTool.fail();
    }

    /**
     * 对评论进行点赞
     * 如果该用户未点赞,则点赞
     * 如果该用户已点赞,则取消点赞
     *
     * @param commentId 博客id
     * @param userId    用户id
     * @return 该用户是否更改点赞状态
     */
    @PostMapping("/comment")
    @ApiOperation("对评论进行点赞")
    public JsonResult likeComment(Long commentId, Integer userId) {
        Boolean like = commentLikesService.likeComment(commentId, userId);
        return like ? ResultTool.success() : ResultTool.fail();

    }

}
