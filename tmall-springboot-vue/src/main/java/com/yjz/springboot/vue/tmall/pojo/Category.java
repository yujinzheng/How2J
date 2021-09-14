package com.yjz.springboot.vue.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: category实体类
 * @author: YU
 * @create: 2021-09-07 03:43
 **/
@Getter
@Setter
public class Category {
    private int categoryId;
    private String imagePath;
    private String name;
}
