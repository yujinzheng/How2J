package com.yjz.springboot.vue.tmall.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.yjz.springboot.vue.tmall.entity.data.CategoryRspData;
import com.yjz.springboot.vue.tmall.entity.errorcode.ErrorCode;
import com.yjz.springboot.vue.tmall.entity.request.CategoryRequest;
import com.yjz.springboot.vue.tmall.entity.response.BaseResponse;
import com.yjz.springboot.vue.tmall.entity.response.DataResponse;
import com.yjz.springboot.vue.tmall.mapper.CategoryMapper;
import com.yjz.springboot.vue.tmall.pojo.Category;
import com.yjz.springboot.vue.tmall.service.CategoryService;

import com.yjz.springboot.vue.tmall.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * @description: 分类查询业务实现类
 *
 * @author: YU
 * @create: 2021-09-15
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private static final int BUFFER = 5120;

    // TODO 后续要做成配置项
    private static final String IMG_DIR = "E:\\work\\java\\How2J\\tmall-springboot-vue\\img\\category";

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public DataResponse findAll(DataResponse response) {
        Page<Category> categoryPage = (Page<Category>)categoryMapper.findAllCategory();
        CategoryRspData data = new CategoryRspData();
        data.setTotal(categoryPage.getTotal());
        data.setCurrentPage(categoryPage.getPageNum());
        data.setCategoryList(categoryPage.getResult());
        response.setData(JSON.toJSONString(data));
        return response;
    }

    @Override
    public void addOne(MultipartFile file, String name, BaseResponse response) {
        if (name.isEmpty()) {
            response.setCode(ErrorCode.ADMIN_PARAM_ERROR.getCode());
            response.setCode(String.format(ErrorCode.ADMIN_PARAM_ERROR.getDecs(), "request.name"));
            return;
        }
        Category category = new Category();
        category.setName(name);
        categoryMapper.addCategory(category);
        if (file == null) {
            return;
        }
        int id = category.getId();
        String originalFileName = file.getOriginalFilename();
        String extensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
        String filePath = IMG_DIR + File.separator + id + extensionName;
        if(!saveFile(file, filePath, response)) {
            return;
        }
        category.setImagePath("\\category\\" + id + extensionName);
        categoryMapper.updateCategory(category);
    }

    @Override
    public void deleteOne(CategoryRequest request, BaseResponse response) {
        try {
            int id = Integer.parseInt(request.getId());
            String imagePath = request.getImagePath();
            if (imagePath != null && !imagePath.isEmpty()) {
                imagePath = imagePath.replace("category", IMG_DIR);
                File file = new File(imagePath);
                if (!file.delete()) {
                    response.setCode(ErrorCode.ADMIN_DELETE_CATEGORY_ERROR.getCode());
                    response.setDesc(ErrorCode.ADMIN_DELETE_CATEGORY_ERROR.getDecs());
                    return;
                }
            }
            categoryMapper.deleteCategory(id);
        } catch (NumberFormatException e) {
            response.setCode(ErrorCode.ADMIN_PARAM_ERROR.getCode());
            response.setDesc(String.format(ErrorCode.ADMIN_PARAM_ERROR.getDecs(), "request.id"));
        }
    }

    @Override
    public void updateOne(MultipartFile file, Category category, BaseResponse response) {
        int id;
        try {
            id = category.getId();
        } catch (NumberFormatException e) {
            response.setCode(ErrorCode.ADMIN_PARAM_ERROR.getCode());
            response.setDesc(String.format(ErrorCode.ADMIN_PARAM_ERROR.getDecs(), "request.category.id"));
            return;
        }
        String name = category.getName();
        String imagePath = category.getImagePath();
        if (name.isEmpty()) {
            response.setCode(ErrorCode.ADMIN_PARAM_ERROR.getCode());
            response.setCode(String.format(ErrorCode.ADMIN_PARAM_ERROR.getDecs(), "request.name"));
            return;
        }

        if (file != null) {
            String originalFileName = file.getOriginalFilename();
            String extensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
            String filePath = IMG_DIR + File.separator + id + extensionName;
            if (!saveFile(file, filePath, response)) {
                return;
            }
            category.setImagePath("\\category\\" + id + extensionName);
        } else {
            if (imagePath != null && !imagePath.isEmpty()) {
                imagePath = imagePath.replace("category", IMG_DIR);
                File currentFile = new File(imagePath);
                if (currentFile.exists() && !currentFile.delete()) {
                    response.setCode(ErrorCode.ADMIN_DELETE_CATEGORY_ERROR.getCode());
                    response.setDesc(ErrorCode.ADMIN_DELETE_CATEGORY_ERROR.getDecs());
                    return;
                }
            }
            category.setImagePath("");
        }
        categoryMapper.updateCategory(category);
    }

    private boolean saveFile(MultipartFile file, String filePath, BaseResponse response) {
        File imgFile = new File(filePath);
        try {
            if (!imgFile.exists()) {
                if (!imgFile.createNewFile()) {
                    throw new IOException();
                }
            }
        } catch (IOException e) {
            response.setCode(ErrorCode.ADMIN_SAVE_CATEGORY_IMG_ERROR.getCode());
            response.setCode(ErrorCode.ADMIN_SAVE_CATEGORY_IMG_ERROR.getDecs());
            return false;
        }
        try(InputStream inputStream = file.getInputStream();
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(imgFile))) {
            byte[] buffer = new byte[BUFFER];
            int length = inputStream.read(buffer, 0, BUFFER);
            while (length != -1) {
                outputStream.write(buffer, 0, BUFFER);
                length = inputStream.read(buffer, 0, BUFFER);
            }
        } catch (IOException e) {
            response.setCode(ErrorCode.ADMIN_ADD_CATEGORY_ERROR.getCode());
            response.setCode(ErrorCode.ADMIN_ADD_CATEGORY_ERROR.getDecs());
            return false;
        }
        return true;
    }
}
