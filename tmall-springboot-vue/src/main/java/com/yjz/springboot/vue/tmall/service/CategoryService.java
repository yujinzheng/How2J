package com.yjz.springboot.vue.tmall.service;

import com.yjz.springboot.vue.tmall.entity.response.BaseResponse;
import com.yjz.springboot.vue.tmall.entity.response.DataResponse;
import com.yjz.springboot.vue.tmall.pojo.Category;

import java.util.List;

/**
 * 分类管理业务接口
 * @author YU
 * @create 2021-09-15
 */
public interface CategoryService {
    DataResponse findAll(DataResponse response);

    BaseResponse addOne(Category category);

    BaseResponse deleteOne(Category category);
}
