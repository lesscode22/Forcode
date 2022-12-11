package com.forcode.base.spring.security;

import com.forcode.base.spring.security.callback.*;
import com.forcode.base.spring.security.extension.VerifyCodeAuthProvider;
import com.forcode.base.spring.security.jwt.JwtAuthenticationTokenFilter;
import com.forcode.base.spring.security.permission.DefaultPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

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
    @Resource
    private UserDetailsService sysUserService;
    @Autowired
    private DefaultPermissionEvaluator defaultPermissionEvaluator;

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
                // 设置重写后的权限注解校验
                .expressionHandler(defaultWebSecurityExpressionHandler())
                // OPTIONS请求全部放行
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 登录、验证码、open接口放行
                .antMatchers("/sms/login", "/open/**").permitAll()
                // 静态资源放行
                .antMatchers("/js/**", "/css/**", "/images/**",
                        "/doc.html", "/webjars/**", "/swagger-resources/**", "/resources/**", "/v2/api-docs/**").permitAll()
                // 其他接口全部接受验证
                .anyRequest().authenticated()
                .and().formLogin().successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailHandler())
                .and().logout().logoutSuccessHandler(new LoginOutHandler())
                .and().exceptionHandling()
                .authenticationEntryPoint(new NoCredentialHandler())
                .accessDeniedHandler(new NoPermissionHandler());

        // 自定义登录认证配置
        http.apply(extendAuthConfig);
        // 添加认证过滤器, 验证token是否合法
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 提供默认username登录查询用户实现
        auth.userDetailsService(sysUserService);
        // 默认 DaoAuthenticationProvider 校验用户前增加验证码校验
        auth.authenticationProvider(new VerifyCodeAuthProvider());
    }

    @Bean
    public DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(defaultPermissionEvaluator);
        return handler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
