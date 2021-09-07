package com.yjz.springboot.vue.tmall.service.impl;

import com.yjz.springboot.vue.tmall.mapper.CategoryMapper;
import com.yjz.springboot.vue.tmall.pojo.Category;
import com.yjz.springboot.vue.tmall.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    public void addOne(Category category) {
        categoryMapper.addOne(category);
    }
}
