package com.forcode.base.spring.security.controller;

import cn.hutool.core.util.IdUtil;
import com.forcode.base.common.Result;
import com.forcode.base.spring.security.permission.DefaultPermissionEvaluator;
import com.forcode.base.spring.security.permission.ExtendPermissionEvaluator;
import com.forcode.base.spring.security.userdetails.sysuser.SysUserInfo;
import com.forcode.base.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-11-27
 **/
@Slf4j
@RestController
@RequestMapping
public class LoginController {

    @GetMapping("getUser")
    public Result getUser() {
        currentUser();
        return Result.ok(SysUserInfo.getInstance());
    }

    @GetMapping("/open/getId")
    public Result getId() {
        currentUser();
        return Result.ok(IdUtil.getSnowflakeNextId());
    }

    /**
     * {@link SecurityExpressionRoot#hasAuthority(String)}
     */
    @PreAuthorize("hasAuthority('sys:user:add')")
    @GetMapping("getUser2")
    public Result getUser2() {
        currentUser();
        return Result.ok(SysUserInfo.getInstance());
    }

    @PreAuthorize("hasAuthority('sys:user:query')")
    @GetMapping("getUser3")
    public Result getUser3() {
        currentUser();
        return Result.ok(SysUserInfo.getInstance());
    }

    /**
     * {@link ExtendPermissionEvaluator#eval(String)}
     */
    @PreAuthorize("@ps.eval('sys:user:delete')")
    @GetMapping("getUser4")
    public Result getUser4() {
        currentUser();
        return Result.ok(SysUserInfo.getInstance());
    }

    /**
     * {@link DefaultPermissionEvaluator#hasPermission(Authentication, Object, Object)}
     * 注意参数对应
     */
    @PreAuthorize("hasPermission('sys:user:move', 'ROLE_USER')")
    @GetMapping("getUser5")
    public Result getUser5() {
        currentUser();
        return Result.ok(SysUserInfo.getInstance());
    }

    public void currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("======= 当前登录用户: {}", JsonUtil.toJsonPretty(authentication));
    }
}
