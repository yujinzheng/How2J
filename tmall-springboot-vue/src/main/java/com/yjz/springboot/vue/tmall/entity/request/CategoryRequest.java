package com.yjz.springboot.vue.tmall.entity.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 分类管理请求体
 * @author: YU
 * @create: 2021-09-21 03:00
 **/
@Getter
@Setter
public class CategoryRequest extends BaseRequest {
    private String id;
    private String name;
    private String imagePath;
}
