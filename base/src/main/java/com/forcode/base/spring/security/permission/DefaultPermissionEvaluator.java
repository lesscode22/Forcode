package com.forcode.base.spring.security.permission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 重写默认校验逻辑
 */
@Slf4j
@Component
public class DefaultPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        log.info("=========== 权限验证-1: {}, {}", targetDomainObject, permission);
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        log.info("=========== 权限验证-2: {}, {}, {}", targetId, targetType, permission);
        return true;
    }
}
