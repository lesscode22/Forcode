package com.forcode.base.spring.security.controller;

import cn.hutool.core.util.IdUtil;
import com.forcode.base.common.Result;
import com.forcode.base.spring.security.userdetails.sysuser.SysUserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-11-27
 **/
@RestController
@RequestMapping
public class LoginController {

    @PostMapping("login")
    public Result login() {
        System.out.println("登录================");
        return Result.ok();
    }

//    @PostMapping("/sms/login")
//    public Result smsLogin() {
//        System.out.println("短信登录================");
//        return Result.ok();
//    }

    @GetMapping("getUser")
    public Result getUser() {
        return Result.ok(SysUserInfo.getInstance());
    }

    @GetMapping("/open/getId")
    public Result getId() {
        System.out.println(IdUtil.getSnowflakeNextIdStr());
        return Result.ok(IdUtil.getSnowflakeNextId());
    }
}