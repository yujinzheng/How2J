
package com.yjz.springboot.vue.tmall.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yjz.springboot.vue.tmall.entity.response.BaseResponse;
import com.yjz.springboot.vue.tmall.entity.response.DataResponse;
import com.yjz.springboot.vue.tmall.pojo.Category;
import com.yjz.springboot.vue.tmall.service.CategoryService;
import com.yjz.springboot.vue.tmall.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        DataResponse response = (DataResponse)ResponseUtils.dataSuccess("");
        PageHelper.startPage(start, size);
        categoryService.findAll(response);
        return response;
    }

    @RequestMapping("/category/add")
    public void addOne(@RequestBody Category category) {
        categoryService.addOne(category);
    }
}
