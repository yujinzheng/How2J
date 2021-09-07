package com.yjz.springboot.vue.tmall.service;

import com.yjz.springboot.vue.tmall.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    void addOne(Category category);
}
