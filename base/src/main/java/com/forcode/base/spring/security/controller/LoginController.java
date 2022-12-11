package com.forcode.base.spring.security.controller;

import cn.hutool.core.util.IdUtil;
import com.forcode.base.common.Result;
import com.forcode.base.spring.security.userdetails.sysuser.SysUserInfo;
import com.forcode.base.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
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

    public void currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("======= 当前登录用户: {}", JsonUtil.toJsonPretty(authentication));
    }
}
