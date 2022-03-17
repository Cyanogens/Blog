package com.turingcourt.dao;

import com.turingcourt.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    @Select("select id, categoryname from turingblog.category where id = #{id}")
    Category queryCategoryById(Integer id);

    /**
     * 根据标签名查询标签
     *
     * @param name 标签名
     * @return 标签类
     */
    @Select("select id, categoryname from turingblog.category where categoryname = #{name}")
    Category queryCategoryByName(String name);

    /**
     * 获得所有标签
     *
     * @return 标签集合
     */
    @Select("select id, categoryname from turingblog.category")
    List<Category> queryAll();

    /**
     * 添加标签内容
     *
     * @param category 实例对象
     * @return 影响行数
     */
    int insertCategory(Category category);

    /**
     * 修改标签内容
     *
     * @param category 实例对象
     * @return 影响行数
     */
    int updateCategory(Category category);

    /**
     * 删除标签内容
     *
     * @param id 主键
     * @return 影响行数
     */
    @Delete("delete from turingblog.category where id = #{id}")
    int deleteCategory(Long id);

    /**
     * 通过博客id查找标签集合
     *
     * @param blogId 博客
     * @return 标签集合
     */
    List<Category> queryByBlogId(Long blogId);

}
