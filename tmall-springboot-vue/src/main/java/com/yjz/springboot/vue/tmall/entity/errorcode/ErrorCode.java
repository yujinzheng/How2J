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
     */
    SUCCESS("0000000", "success"),
    ADMIN_DATABASE_ERROR("3001001", "get category list failed: database error");

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
