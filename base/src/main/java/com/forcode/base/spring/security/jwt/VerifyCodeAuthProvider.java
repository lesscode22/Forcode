package com.forcode.base.spring.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 登录验证码校验
 *
 * @author: TJ
 * @date:  2022-11-30
 **/
@Slf4j
public class VerifyCodeAuthProvider extends DaoAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) {
        log.info("============ 自定义验证码校验");
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String code = req.getParameter("code");
        String verifyCode = (String) req.getSession().getAttribute("verify_code");
        // TODO

        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
