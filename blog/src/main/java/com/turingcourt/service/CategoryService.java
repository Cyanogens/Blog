package com.turingcourt.service;

import com.github.pagehelper.PageInfo;
import com.turingcourt.entity.Category;

/**
 * (Category)表服务接口
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
public interface CategoryService {

    /**
     * 根据id查询标签
     *
     * @param id 标签id
     * @return 标签类
     */
    Category queryCategoryById(Integer id);

    /**
     * 获得所有标签
     *
     * @return 标签集合
     */
    PageInfo<Category> queryAll();

    /**
     * 添加标签
     * 要判断是否已经存在
     *
     * @param category 实例对象
     * @return 是否添加成功
     */
    Boolean insertCategory(Category category);

    /**
     * 修改标签
     *
     * @param category 实例对象
     * @return 是否修改成功
     */
    Boolean updateCategory(Category category);

    /**
     * 删除标签
     *
     * @param id 主键
     * @return 是否删除成功
     */
    Boolean deleteCategory(Integer id);
}
