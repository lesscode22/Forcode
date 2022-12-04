package com.forcode.base.spring.security;

import com.forcode.base.spring.security.auth.JwtAuthenticationTokenFilter;
import com.forcode.base.spring.security.callback.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description:
 *
 * @author: TJ
 * @date: 2022-10-03
 **/
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true) // 开启方法权限注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ExtendAuthConfig extendAuthConfig;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 新版已不推荐在 WebSecurity 配置忽略的路径
     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
//    }

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
                // OPTIONS请求全部放行
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 登录、验证码、open接口放行
                .antMatchers("/auth/login", "/sms/login", "/open/**").permitAll()
                // 静态资源放行
                .antMatchers("/js/**", "/css/**", "/images/**",
                        "/doc.html", "/webjars/**", "/swagger-resources/**", "/resources/**", "/v2/api-docs/**").permitAll()
                // 其他接口全部接受验证
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // 登录成功回调
                .successHandler(new LoginSuccessHandler())
                // 登录失败回调
                .failureHandler(new LoginFailHandler())
                .and()
                .logout()
                // 注销回调
                .logoutSuccessHandler(new LoginOutHandler())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new NoCredentialHandler())
                .accessDeniedHandler(new NoPermissionHandler());

        // 自定义登录认证配置
        http.apply(extendAuthConfig);
        // 添加认证过滤器, 验证token是否合法
//        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
