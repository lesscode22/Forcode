package com.forcode.base.common.resp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> ok() {
        return new Result<T>().setCode(200).setMessage("success");
    }

    @SuppressWarnings("unchecked")
    public <TA> Result<TA> setData(T data) {
        this.data = data;
        return (Result<TA>) this;
    }
}
