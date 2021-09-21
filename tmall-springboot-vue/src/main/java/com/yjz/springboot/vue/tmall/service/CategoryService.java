package com.yjz.springboot.vue.tmall.service;

import com.yjz.springboot.vue.tmall.entity.request.CategoryRequest;
import com.yjz.springboot.vue.tmall.entity.response.BaseResponse;
import com.yjz.springboot.vue.tmall.entity.response.DataResponse;
import com.yjz.springboot.vue.tmall.pojo.Category;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 分类管理业务接口
 * @author YU
 * @create 2021-09-15
 */
public interface CategoryService {
    DataResponse findAll(DataResponse response);

    void addOne(MultipartFile file, String name, BaseResponse response);

    void deleteOne(CategoryRequest request, BaseResponse response);

    void updateOne(MultipartFile file, Category category, BaseResponse response);
}
