package com.yjz.springboot.vue.tmall.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.yjz.springboot.vue.tmall.entity.data.CategoryRspData;
import com.yjz.springboot.vue.tmall.entity.response.BaseResponse;
import com.yjz.springboot.vue.tmall.entity.response.DataResponse;
import com.yjz.springboot.vue.tmall.mapper.CategoryMapper;
import com.yjz.springboot.vue.tmall.pojo.Category;
import com.yjz.springboot.vue.tmall.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 分类查询业务实现类
 *
 * @author: YU
 * @create: 2021-09-15
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    public DataResponse findAll(DataResponse response) {
        Page<Category> categoryPage = (Page<Category>)categoryMapper.findAll();
        CategoryRspData data = new CategoryRspData();
        data.setTotal(categoryPage.getTotal());
        data.setCurrentPage(categoryPage.getPageNum());
        data.setCategoryList(categoryPage.getResult());
        response.setData(JSON.toJSONString(data));
        return response;
    }

    public void addOne(Category category) {
        categoryMapper.addOne(category);
    }
}
