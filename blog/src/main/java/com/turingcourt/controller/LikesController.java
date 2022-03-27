package com.turingcourt.controller;

import com.turingcourt.config.json.JsonResult;
import com.turingcourt.config.json.ResultTool;
import com.turingcourt.service.BlogLikesService;
import com.turingcourt.service.CommentLikesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 点赞
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
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
    public JsonResult likeBlog(@NotNull(message = "博客id不应为空") Long blogId,
                               @NotNull(message = "用户id不应为空") Integer userId) {
        try {
            Boolean like = blogLikesService.likeBlog(blogId, userId);
            if (like) {
                return ResultTool.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail();
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
    public JsonResult likeComment(@NotNull(message = "评论id不应为空") Long commentId,
                                  @NotNull(message = "用户id不应为空") Integer userId) {
        try {
            Boolean like = commentLikesService.likeComment(commentId, userId);
            if (like) {
                ResultTool.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail();

    }

}
