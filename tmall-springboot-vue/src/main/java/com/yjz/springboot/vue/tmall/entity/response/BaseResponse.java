package com.yjz.springboot.vue.tmall.entity.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 响应基类
 * @author: YU
 * @create: 2021-09-18 23:48
 **/
@Getter
@Setter
public class BaseResponse {
    private String code;
    private String desc;
}
