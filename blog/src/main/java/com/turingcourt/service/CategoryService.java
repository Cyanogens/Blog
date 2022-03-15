package com.turingcourt.service;

import com.turingcourt.entity.Category;

import java.util.List;

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
     * 获得所有标签内容
     *
     * @return 标签集合
     */
    List<Category> queryAll();

    /**
     * 添加标签内容
     * 要判断是否已经存在
     *
     * @param category 实例对象
     * @return 是否添加成功
     */
    Boolean insertCategory(Category category);

    /**
     * 修改标签内容
     * 要判断修改后的内容是否已经存在
     *
     * @param category 实例对象
     * @return 是否修改成功
     */
    Boolean updateCategory(Category category);

    /**
     * 删除标签内容
     * 博客-标签表也要对应的删除
     *
     * @param id 主键
     * @return 是否删除成功
     */
    Boolean deleteCategory(Long id);
}
