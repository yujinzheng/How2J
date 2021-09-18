package com.yjz.springboot.vue.tmall.entity.data;

import com.yjz.springboot.vue.tmall.pojo.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description: 分类信息返回体数据实体类
 * @author: YU
 * @create: 2021-09-19 00:52
 **/
@Getter
@Setter
public class CategoryRspData {
    private long total;
    private int currentPage;
    private List<Category> categoryList;
}