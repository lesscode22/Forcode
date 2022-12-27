package com.forcode.auth.config;

import com.forcode.auth.callback.*;
import com.forcode.auth.detail.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf防护
        http.csrf().disable();
        // 关闭session认证
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 支持iframe嵌入
        // X-Frame-Options 响应头是用来告诉浏览器是否允许一个页面在<frame>,<iframe>,<embed>或者<object>中展现，通过该响应头可以确保网站没有被嵌入到其他站点中，进而避免发生打击劫持
        http.headers().frameOptions().disable();

        // 基本设置
        http.authorizeRequests()
                .antMatchers( "/test/**").permitAll()
                // 其他接口全部接受验证
                .anyRequest().authenticated()
                .and().formLogin().successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailHandler())
                .and().logout().logoutSuccessHandler(new LoginOutHandler())
                .and().exceptionHandling()
                .authenticationEntryPoint(new NoCredentialHandler())
                .accessDeniedHandler(new NoPermissionHandler());

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 提供默认username登录查询用户实现
        auth.userDetailsService(sysUserService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
