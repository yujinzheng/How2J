package com.yjz.springboot.vue.tmall.controller;

import com.yjz.springboot.vue.tmall.pojo.Category;
import com.yjz.springboot.vue.tmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: YU
 * @create: 2021-09-07 03:59
 **/
@RestController
@RequestMapping("/tmall-vue/admin")
public class AdminController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category/list")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @RequestMapping("/category/add")
    public void addOne(@RequestBody Category category) {
        categoryService.addOne(category);
    }
}
