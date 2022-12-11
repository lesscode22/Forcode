package com.forcode.base.spring.security.permission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 自定义校验方法
 */
@Slf4j
@Component("ps")
public class ExtendPermissionEvaluator {

    public boolean eval(String expression) {

        log.info("============ 自定义权限校验: {}", expression);
        return true;
    }
}
