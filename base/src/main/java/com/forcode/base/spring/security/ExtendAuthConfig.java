package com.forcode.base.spring.security;

import com.forcode.base.spring.security.callback.LoginFailHandler;
import com.forcode.base.spring.security.callback.LoginSuccessHandler;
import com.forcode.base.spring.security.extension.sms.SmsCodeAuthenticationProcessingFilter;
import com.forcode.base.spring.security.extension.sms.SmsCodeAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 扩展登录
 */
@Component
public class ExtendAuthConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Resource
    private UserDetailsService memberUserService;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // 手机号 + 短信验证码登录
        SmsCodeAuthenticationProcessingFilter smsCodeFilter = new SmsCodeAuthenticationProcessingFilter();
        smsCodeFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
        smsCodeFilter.setAuthenticationFailureHandler(new LoginFailHandler());

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(memberUserService);
        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
