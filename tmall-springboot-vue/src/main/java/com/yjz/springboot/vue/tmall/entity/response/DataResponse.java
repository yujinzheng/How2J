package com.yjz.springboot.vue.tmall.entity.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 数据类型的响应体
 * @author: YU
 * @create: 2021-09-18 23:51
 **/
@Getter
@Setter
public class DataResponse extends BaseResponse {
    private String data;
}
