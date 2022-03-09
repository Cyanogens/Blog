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
     * 博客浏览量增加
     *
     * @param blogId 博客id
     * @return 是否成功
     */
    @PostMapping("/changeView")
    @ApiOperation("浏览量增加")
    public JsonResult viewBlog(Long blogId){
        return null;
    }

    /**
     * 获得随机博客列表
     *
     * @return 随机博客列表
     */
    @GetMapping("/random")
    @ApiOperation("获得随机博客列表")
    public JsonResult blogRandomList(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="10")int pageSize){
        return null;
    }

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
    public JsonResult searchBlog(String key, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return null;
    }

}
