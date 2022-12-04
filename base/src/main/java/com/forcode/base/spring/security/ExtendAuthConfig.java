package com.forcode.base.spring.security;

import com.forcode.base.spring.security.callback.LoginFailHandler;
import com.forcode.base.spring.security.callback.LoginSuccessHandler;
import com.forcode.base.spring.security.extension.sms.SmsCodeAuthenticationProcessingFilter;
import com.forcode.base.spring.security.extension.sms.SmsCodeAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 扩展登录
 */
@Component
public class ExtendAuthConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // 手机号 + 短信验证码登录
        SmsCodeAuthenticationProcessingFilter smsCodeFilter = new SmsCodeAuthenticationProcessingFilter();
        smsCodeFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
        smsCodeFilter.setAuthenticationFailureHandler(new LoginFailHandler());

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        // 将短信验证码校验器注册到 HttpSecurity,  并将短信验证码过滤器添加在 UsernamePasswordAuthenticationFilter 之前
        http.authenticationProvider(smsCodeAuthenticationProvider).addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
