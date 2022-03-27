package com.turingcourt.controller;

import com.github.pagehelper.PageInfo;
import com.turingcourt.config.json.JsonResult;
import com.turingcourt.config.json.ResultTool;
import com.turingcourt.entity.User;
import com.turingcourt.service.UserService;
import com.turingcourt.vo.BlogVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * User控制层
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Validated
@RestController
@ApiOperation("用户操作")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/{userId}")
    @ApiOperation("查询用户信息")
    public JsonResult getUser(@NotNull(message = "用户id不应为空") @PathVariable Integer userId) {
        try {
            User user = userService.getUser(userId);
            if (user != null) {
                return ResultTool.success(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail();
    }

    /**
     * 更改用户信息
     *
     * @param user 更新后的用户信息
     * @return 用户信息
     */
    @PostMapping("/change")
    @ApiOperation("更改用户信息")
    public JsonResult changeUser(@Validated @RequestBody User user) {
        try {
            User newUser = userService.changeUser(user);
            if (newUser != null) {
                return ResultTool.success(newUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail();
    }

    /**
     * 查询用户发布的全部博客
     * 通过用户id查找博客
     *
     * @param userId   用户id
     * @param pageNo   要显示第几页内容
     * @param pageSize 一页显示多少条
     * @return 博客列表
     */
    @GetMapping("/userBlogs/{userId}")
    @ApiOperation("查询用户发布的所有博客")
    public JsonResult userBlogs(@NotNull(message = "用户id不应为空") @PathVariable Integer userId,
                                @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        try {
            PageInfo<BlogVO> blogVOPageInfo = userService.userBlogs(userId, pageNo, pageSize);
            if (blogVOPageInfo != null) {
                return ResultTool.success(blogVOPageInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail();
    }


    /**
     * 更改用户发布的博客
     *
     * @param blogId 博客id
     * @param blogVO 更新后的博客
     * @return 是否更新成功
     */
    @PostMapping("/changeBlog/{blogId}")
    @ApiOperation("更改用户发布的博客")
    public JsonResult changeUserBlog(@Validated @RequestBody BlogVO blogVO,
                                     @NotNull(message = "博客id不应为空") @PathVariable Long blogId) {
        try {
            Boolean change = userService.changeUserBlog(blogVO, blogId);
            if (change) {
                return ResultTool.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail();
    }

    /**
     * 删除用户发布的博客
     *
     * @param blogId 用户id
     * @return 是否删除成功
     */
    @PostMapping("/deleteBlog/{blogId}")
    @ApiOperation("删除用户发布的博客")
    public JsonResult deleteUserBlog(@NotNull(message = "博客id不应为空") Long blogId) {
        try {
            Boolean delete = userService.deleteUserBlog(blogId);
            if (delete) {
                return ResultTool.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail();
    }
}
