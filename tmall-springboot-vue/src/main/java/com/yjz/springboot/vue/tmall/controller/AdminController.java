
package com.yjz.springboot.vue.tmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.github.pagehelper.PageHelper;
import com.yjz.springboot.vue.tmall.entity.errorcode.ErrorCode;
import com.yjz.springboot.vue.tmall.entity.request.CategoryRequest;
import com.yjz.springboot.vue.tmall.entity.response.BaseResponse;
import com.yjz.springboot.vue.tmall.entity.response.DataResponse;
import com.yjz.springboot.vue.tmall.pojo.Category;
import com.yjz.springboot.vue.tmall.service.CategoryService;
import com.yjz.springboot.vue.tmall.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 管理页面controller
 * 
 * @author YU
 * @create 2021-09-07 03:59
 **/
@RestController
@RequestMapping("/tmall-vue/admin")
public class AdminController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category/list")
    public BaseResponse findAll(@RequestParam(value = "start", defaultValue = "1") int start,
        @RequestParam(value = "size", defaultValue = "10") int size) {
        DataResponse response = (DataResponse) ResponseUtils.dataSuccess("");
        PageHelper.startPage(start, size);
        categoryService.findAll(response);
        return response;
    }

    @RequestMapping("/category/add")
    public BaseResponse addOne(@RequestPart(value = "picture", required = false) MultipartFile picture,
        @RequestPart("name") String name) {
        BaseResponse response = ResponseUtils.success();
        categoryService.addOne(picture, name, response);
        return response;
    }

    @RequestMapping("/category/delete")
    public BaseResponse deleteOne(@RequestBody CategoryRequest request) {
        BaseResponse response = ResponseUtils.success();
        categoryService.deleteOne(request, response);
        return response;
    }

    @RequestMapping("/category/update")
    public BaseResponse updateOne(@RequestParam(value = "picture", required = false) MultipartFile picture,
        @RequestParam(value = "category") String categoryStr) {
        BaseResponse response = ResponseUtils.success();
        Category category = JSONObject.parseObject(categoryStr, Category.class);
        if (category == null) {
            response.setCode(ErrorCode.ADMIN_PARAM_ERROR.getCode());
            response.setDesc(String.format(ErrorCode.ADMIN_PARAM_ERROR.getDecs(),"request.category"));
            return response;
        }
        categoryService.updateOne(picture, category, response);
        return response;
    }
}
