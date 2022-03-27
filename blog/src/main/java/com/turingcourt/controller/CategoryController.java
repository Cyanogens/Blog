package com.turingcourt.controller;

import com.turingcourt.config.json.JsonResult;
import com.turingcourt.config.json.ResultTool;
import com.turingcourt.entity.Category;
import com.turingcourt.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Category控制层
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@RestController
@ApiOperation("标签操作")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获得所有标签
     *
     * @return 标签集合
     */
    @GetMapping("/getAll")
    @ApiOperation("获得所有标签")
    public JsonResult getAllCategory() {
        try {
            List<Category> categories = categoryService.queryAll();
            if (categories != null) {
                return ResultTool.success(categories);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail();
    }

}
