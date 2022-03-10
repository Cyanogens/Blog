package com.turingcourt.service.impl;

import com.github.pagehelper.PageInfo;
import com.turingcourt.dao.CategoryDao;
import com.turingcourt.entity.Category;
import com.turingcourt.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Category)表服务实现类
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;

    @Override
    public Category queryCategoryById(Integer id) {
        return null;
    }

    @Override
    public PageInfo<Category> queryAll() {
        return null;
    }

    @Override
    public Boolean insertCategory(Category category) {
        return null;
    }

    @Override
    public Boolean updateCategory(Category category) {
        return null;
    }

    @Override
    public Boolean deleteCategory(Integer id) {
        return null;
    }
}
