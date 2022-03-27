package com.turingcourt.controller;

import com.github.pagehelper.PageInfo;
import com.turingcourt.config.json.JsonResult;
import com.turingcourt.config.json.ResultCode;
import com.turingcourt.config.json.ResultTool;
import com.turingcourt.service.BlogService;
import com.turingcourt.vo.BlogVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Blog控制层
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:52
 */
@Validated
@RestController
@ApiOperation("博客操作")
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 博客浏览量增加
     *
     * @param blogId 博客id
     * @return 浏览量
     */
    @PostMapping("/changeView")
    @ApiOperation("浏览量增加")
    public JsonResult viewBlog(@NotNull(message = "博客id不应为空") Long blogId) {
        System.out.println("blogId = " + blogId);
        try {
            Long view = blogService.viewBlog(blogId);
            if (view > 0) {
                return ResultTool.success(view);
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return ResultTool.fail();
    }

    /**
     * 获得随机博客列表
     *
     * @param pageNo   要显示第几页内容
     * @param pageSize 一页显示多少条
     * @return 随机博客列表
     */
    @GetMapping("/random")
    @ApiOperation("获得随机博客列表")
    public JsonResult blogRandomList(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        try {
            PageInfo<BlogVO> pageInfo = blogService.blogRandomList(pageNo, pageSize);
            if (pageInfo != null) {
                return ResultTool.success(pageInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail();
    }

    /**
     * 查看博客详情
     *
     * @param blogId 博客id
     * @return 博客内容
     */
    @GetMapping("/{blogId}")
    @ApiOperation("查看博客详情")
    public JsonResult blogDetail(@NotNull(message = "博客id不应为空") @PathVariable Long blogId) {
        try {
            BlogVO blogVO = blogService.blogDetail(blogId);
            if (blogVO != null) {
                return ResultTool.success(blogVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail(ResultCode.COMMON_FAIL, "无该博客");
    }

    /**
     * 发布博客
     *
     * @param blogVO 博客内容
     * @return 博客id
     */
    @PostMapping("/insertBlog")
    @ApiOperation("发布博客")
    public JsonResult insertBlog(@Validated @RequestBody BlogVO blogVO) {
        try {
            Long blogId = blogService.insertBlog(blogVO);
            if (blogId > 0) {
                return ResultTool.success(blogId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail(ResultCode.COMMON_FAIL, "发布失败");
    }

    /**
     * 搜索博客/标签
     *
     * @param key      搜索关键字
     * @param pageNo   要显示第几页内容
     * @param pageSize 一页显示多少条
     * @return 博客集合
     */
    @GetMapping("/search")
    @ApiOperation("搜索博客/标签")
    public JsonResult searchBlog(@NotBlank(message = "关键字不应为空") @RequestParam("key") String key,
                                 @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        try {
            PageInfo<BlogVO> pageInfo = blogService.searchBlog(key, pageNo, pageSize);
            if (pageInfo != null) {
                return ResultTool.success(pageInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail();
    }
}
