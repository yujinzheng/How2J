package com.yjz.springboot.vue.tmall.mapper;

import com.yjz.springboot.vue.tmall.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * category表mapper接口
 * @author YU
 */
@Mapper
@Repository
public interface CategoryMapper {
    public List<Category> findAll();

    public void deleteOne(int categoryId);

    public void addOne(Category category);
}
