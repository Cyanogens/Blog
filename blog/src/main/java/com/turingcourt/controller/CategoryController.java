package com.turingcourt.controller;

import com.turingcourt.config.json.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Category控制层
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@CrossOrigin
@RestController
@ApiOperation("标签操作")
@RequestMapping("/category")
public class CategoryController {

    /**
     * 获得所有标签
     *
     * @return 标签集合
     */
    @GetMapping("/getAll")
    @ApiOperation("获得所有标签")
    public JsonResult getAllCategory() {
        return null;
    }

}
