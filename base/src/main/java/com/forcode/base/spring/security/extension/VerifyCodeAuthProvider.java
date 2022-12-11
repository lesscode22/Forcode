package com.forcode.base.spring.security.extension;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @description: 图片验证码校验
 *
 * @author: TJ
 * @date:  2022-11-30
 **/
@Slf4j
@Setter
public class VerifyCodeAuthProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("========== 进入图片验证码校验");

        // 返回null, 使其继续走 DaoAuthenticationProvider校验用户
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
