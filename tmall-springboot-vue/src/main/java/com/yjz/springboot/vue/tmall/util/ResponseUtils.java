package com.yjz.springboot.vue.tmall.util;

import com.yjz.springboot.vue.tmall.entity.errorcode.ErrorCode;
import com.yjz.springboot.vue.tmall.entity.response.BaseResponse;
import com.yjz.springboot.vue.tmall.entity.response.DataResponse;

/**
 * @description: 通用响应构造方法类
 * @author: YU
 * @create: 2021-09-19 00:19
 **/
public class ResponseUtils {
    public static BaseResponse success() {
        BaseResponse response = new BaseResponse();
        response.setCode(ErrorCode.SUCCESS.getCode());
        response.setDesc(ErrorCode.SUCCESS.getDecs());
        return response;
    }

    public static BaseResponse dataSuccess(String data) {
        DataResponse response = new DataResponse();
        response.setCode(ErrorCode.SUCCESS.getCode());
        response.setDesc(ErrorCode.SUCCESS.getDecs());
        response.setData(data);
        return response;
    }
}
