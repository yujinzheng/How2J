package com.yjz.springboot.vue.tmall.entity.errorcode;

/**
 * @description: 错误码
 * @author: YU
 * @create: 2021-09-18 23:56
 **/
public enum ErrorCode {
    /**
     * 错误码组成：错误级别+所属模块+具体错误编号
     *
     * 错误级别：
     * 1：系统错误
     * 2：服务错误
     * 3：用户错误
     *
     * 所属模块：
     * 001：管理后台
     *
     * 具体错误编号：
     * 001：分类管理查询数据库错误
     * 002：新增分类错误
     * 003：删除分类错误
     * 004：修改分类错误
     * 005：查询分类错误
     * 006：存放分类图片错误
     * 011：请求参数错误
     */
    SUCCESS("0000000", "success"),
    ADMIN_DATABASE_ERROR("3001001", "get category list failed: database error"),
    ADMIN_ADD_CATEGORY_ERROR("3001002", "add category failed"),
    ADMIN_DELETE_CATEGORY_ERROR("3001003", "delete category failed"),
    ADMIN_SAVE_CATEGORY_IMG_ERROR("3001006", "save category image failed, can not create file"),
    ADMIN_PARAM_ERROR("3001011", "request param is error: %s");

    private final String code;
    private final String decs;

    ErrorCode(String code, String decs) {
        this.code = code;
        this.decs = decs;
    }

    public String getCode() {
        return code;
    }

    public String getDecs() {
        return decs;
    }
}
