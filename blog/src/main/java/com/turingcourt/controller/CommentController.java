package com.turingcourt.controller;

import com.github.pagehelper.PageInfo;
import com.turingcourt.config.json.JsonResult;
import com.turingcourt.config.json.ResultTool;
import com.turingcourt.service.CommentService;
import com.turingcourt.vo.CommentVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.turingcourt.config.json.ResultCode.COMMON_FAIL;

/**
 * Comment控制层
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@CrossOrigin
@RestController
@ApiOperation("评论操作")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 查询某博客的所有评论
     *
     * @param blogId   博客id
     * @param pageNo   要显示第几页内容
     * @param pageSize 一页显示多少条
     * @return 所有评论
     */
    @GetMapping("/get/{blogId}")
    @ApiOperation("查询某博客的所有评论")
    public JsonResult getAllComment(@PathVariable Long blogId, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<CommentVO> allComment = commentService.getAllComment(blogId, pageNo, pageSize);
        if (allComment != null) {
            return ResultTool.success(allComment);
        } else {
            return ResultTool.fail();
        }

    }

    /**
     * 发布评论
     * 评论完要重新把该博客的所有评论查一遍
     *
     * @param commentVO 评论内容
     * @param blogId    回复的博客id
     * @return 评论是否发布成功
     */
    @PostMapping("/insert")
    @ApiOperation("发布评论")
    public JsonResult insertComment(@RequestBody CommentVO commentVO, Long blogId) {
        Boolean insert = commentService.insertComment(commentVO, blogId);

        return insert ? ResultTool.success() : ResultTool.fail();
    }

    /**
     * 回复评论
     *
     * @param commentVO 回复内容
     * @param pid       评论的id
     * @return 回复是否成功
     */
    @PostMapping("/reply")
    @ApiOperation("回复评论")
    public JsonResult replyComment(@RequestBody CommentVO commentVO, Long pid) {
        Boolean insert = commentService.replyComment(commentVO, pid);

        return insert ? ResultTool.success() : ResultTool.fail();
    }

    /**
     * 删除评论
     *
     * @param id 评论的id
     * @return 删除是否成功
     */
    @PostMapping("/delete")
    @ApiOperation("删除评论")
    public JsonResult deleteComment(Long id) {
        Boolean delete = commentService.deleteComment(id);
        return delete ? ResultTool.success() : ResultTool.fail();
    }
}
