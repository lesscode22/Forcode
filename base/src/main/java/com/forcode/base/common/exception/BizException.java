package com.forcode.base.common.exception;

import cn.hutool.core.util.StrUtil;
import com.forcode.base.common.ResultCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BizException extends RuntimeException{

    private String code;
    private String message;

    public BizException(String message) {
        this.code = ResultCode.SYSTEM_EXECUTION_ERROR.getCode();
        this.message = message;
    }

    /**
     * message中使用 {} 占位符
     * @param message 错误提示信息
     * @param params 参数
     */
    public BizException(String message, Object... params) {
        this.code = ResultCode.SYSTEM_EXECUTION_ERROR.getCode();
        this.message = StrUtil.format(message, params);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "BizException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
