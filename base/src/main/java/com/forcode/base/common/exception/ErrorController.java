package com.forcode.base.common.exception;

import com.forcode.base.common.ResultCode;
import com.google.common.collect.ImmutableMap;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description: 处理过滤器执行中出现的异常
 * 
 * @author: TJ
 * @date:  2022-05-21
 **/
@RestController
public class ErrorController extends BasicErrorController {

    public ErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    public ErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);
    }

    public ErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }

    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {

        // 获取错误信息
        ErrorAttributeOptions attributeOptions = ErrorAttributeOptions.defaults();
        attributeOptions = attributeOptions.including(ErrorAttributeOptions.Include.MESSAGE);
        Map<String, Object> body = getErrorAttributes(request, attributeOptions);
        String message = body.get("message").toString();

        Map<String, Object> res = ImmutableMap.of(
                "code", ResultCode.SERVLET_ERROR.getCode(),
                "message", ResultCode.SERVLET_ERROR.getMessage(),
                "data", message
        );
        return new ResponseEntity<>(res, getStatus(request));
    }
}
