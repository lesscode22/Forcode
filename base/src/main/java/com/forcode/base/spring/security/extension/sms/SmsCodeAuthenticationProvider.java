package com.forcode.base.spring.security.extension.sms;

import cn.hutool.core.util.StrUtil;
import com.forcode.base.utils.AssertUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-12-01
 **/
@Slf4j
@Setter
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("=== 进入短信码认证处理");
        String mobile = StrUtil.toStringOrNull(authentication.getPrincipal());
        AssertUtil.notEmpty(mobile, "手机号不能为空");

        String smsCode = StrUtil.toStringOrNull(authentication.getCredentials());
        AssertUtil.notEmpty(smsCode, "短信验证码不能为空");

        UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);
        return new SmsCodeAuthenticationToken(userDetails, null, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
