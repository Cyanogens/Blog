package com.turingcourt.controller;

import com.turingcourt.config.json.JsonResult;
import com.turingcourt.vo.BlogVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * Blog控制层
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:52
 */
@CrossOrigin
@RestController
@ApiOperation("博客操作")
@RequestMapping("/blog")
public class BlogController {

    /**
     * 查看博客详情
     *
     * @param blogId 博客id
     * @return 博客内容
     */
    @GetMapping("/{blogId}")
    @ApiOperation("查看博客详情")
    public JsonResult blogDetail(@PathVariable Long blogId) {
        return null;
    }

    /**
     * 发布博客
     *
     * @param blogVO 博客内容
     * @return 博客id
     */
    @PostMapping("/insertBlog")
    @ApiOperation("发布博客")
    public JsonResult insertBlog(BlogVO blogVO) {
        return null;
    }

    /**
     * 搜索博客/标签
     *
     * @param key 搜索关键字
     * @return 博客集合
     */
    @GetMapping("/search")
    @ApiOperation("搜索博客/标签")
    public JsonResult searchBlog(String key) {
        return null;
    }

}
