package com.turingcourt.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.turingcourt.config.json.JsonResult;
import com.turingcourt.entity.User;
import com.turingcourt.vo.BlogVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * User控制层
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@CrossOrigin
@RestController
@ApiOperation("用户操作")
@RequestMapping("/user")
public class UserController {

    /**
     * 查询用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/{userId}")
    @ApiOperation("查询用户信息")
    public JsonResult getUser(@PathVariable Integer userId) {
        return null;
    }

    /**
     * 更改用户信息
     *
     * @param user 更新后的用户信息
     * @return 用户信息
     */
    @PostMapping("/change")
    @ApiOperation("更改用户信息")
    public JsonResult changeUser(@RequestBody User user) {
        return null;
    }

    /**
     * 查询用户发布的全部博客
     * 通过用户id查找博客
     *
     * @param userId 用户id
     * @param pageNo 要显示第几页内容
     * @param pageSize 一页显示多少条
     * @return 博客列表
     */
    @GetMapping("/userBlogs/{userId}")
    @ApiOperation("查询用户发布的所有博客")
    public JsonResult userBlogs(@PathVariable Integer userId, @RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="10")int pageSize) {
        return null;
    }


    /**
     * 更改用户发布的博客
     *
     * @param userId 用户id
     * @param blogVO 更新后的博客
     * @return 是否更新成功
     */
    @PostMapping("/changeBlog/{userId}")
    @ApiOperation("更改用户发布的博客")
    public JsonResult changeUserBlog(@RequestBody BlogVO blogVO, @PathVariable Integer userId) {
        return null;
    }

    /**
     * 删除用户发布的博客
     *
     * @param blogId 用户id
     * @return 是否删除成功
     */
    @PostMapping("/deleteBlog/{blogId}")
    @ApiOperation("删除用户发布的博客")
    public JsonResult deleteUserBlog(Long blogId) {
        return null;
    }

}
