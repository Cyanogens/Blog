package com.turingcourt.dao;

import com.turingcourt.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Category)表数据库访问层
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Mapper
public interface CategoryDao {

    /**
     * 根据id查询标签
     *
     * @param id 标签id
     * @return 标签类
     */
    Category queryById(Integer id);

    /**
     * 获得所有标签
     *
     * @return 标签集合
     */
    List<Category> queryAll();

    /**
     * 添加标签
     * 要判断是否已经存在
     *
     * @param category 实例对象
     * @return 影响行数
     */
    int insertCategory(Category category);

    /**
     * 修改标签
     *
     * @param category 实例对象
     * @return 影响行数
     */
    int updateCategory(Category category);

    /**
     * 删除标签
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteCategory(Integer id);

}
