package com.turingcourt.service.impl;

import com.turingcourt.dao.CategoryDao;
import com.turingcourt.entity.Category;
import com.turingcourt.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        return categoryDao.queryCategoryById(id);
    }

    @Override
    public List<Category> queryAll() {
        return categoryDao.queryAll();
    }

    @Override
    public Boolean insertCategory(Category category) {
        return categoryDao.insertCategory(category) > 0;
    }

    @Override
    public Boolean updateCategory(Category category) {
        return categoryDao.updateCategory(category) > 0;
    }

    @Override
    public Boolean deleteCategory(Long id) {
        return categoryDao.deleteCategory(id) > 0;
    }
}
