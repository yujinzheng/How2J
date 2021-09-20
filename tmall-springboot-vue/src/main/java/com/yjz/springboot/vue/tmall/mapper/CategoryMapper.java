package com.yjz.springboot.vue.tmall.mapper;

import com.yjz.springboot.vue.tmall.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * category表mapper接口
 * @author YU
 * @create 2021-09-15
 */
@Mapper
@Repository
public interface CategoryMapper {
    public List<Category> findAllCategory();

    public void deleteCategory(int id);

    public void addCategory(Category category);

    public void updateCategory(Category category);
}
