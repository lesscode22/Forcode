package com.forcode.base.common;

import cn.hutool.core.convert.Convert;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private String code;
    /**
     * 返回提示信息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;
    /**
     * 分页对象
     */
    private CommonPage page;

    private Result() {
    }

    /**
     * 调用成功
     */
    public static Result ok() {
        return restResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null, null);
    }

    public static Result ok(Object data) {
        return restResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data, null);
    }

    public static Result ok(Object data, CommonPage page) {
        return restResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data, page);
    }

    public static Result ok(String code, String message, Object data) {
        return restResult(code, message, data, null);
    }

    public static Result ok(String code, String message, Object data, CommonPage page) {
        return restResult(code, message, data, page);
    }

    /**
     * 调用失败
     */
    public static Result error() {
        return restResult(ResultCode.SYSTEM_EXECUTION_ERROR.getCode(), ResultCode.SYSTEM_EXECUTION_ERROR.getMessage(), null, null);
    }

    public static Result error(String code, String message) {
        return restResult(code, message, null, null);

    }

    public static Result error(Object data) {
        return restResult(ResultCode.SYSTEM_EXECUTION_ERROR.getCode(), ResultCode.SYSTEM_EXECUTION_ERROR.getMessage(), data, null);
    }

    public static Result error(String code, String message, Object data) {
        return restResult(code, message, data, null);
    }

    public static Result judge(boolean status) {
        if (status) {
            return ok();
        } else {
            return error();
        }
    }

    public <T> T convertDataTo(Class<T> clazz) {
        return Convert.convert(clazz, this.getData());
    }

    private static Result restResult(String code, String msg, Object data, CommonPage page) {
        Result result = new Result();
        result.setCode(code);
        result.setData(data);
        result.setMessage(msg);
        result.setPage(page);
        return result;
    }
}
