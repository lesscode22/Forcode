package com.forcode.base.spring.security.jwt;

import cn.hutool.core.util.StrUtil;
import com.forcode.base.spring.security.extension.sms.SmsCodeAuthenticationToken;
import com.forcode.base.utils.AssertUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: token校验过滤, 参考 {@link BasicAuthenticationFilter}
 *  优先级会高于自定义的登录认证过滤器
 *
 * @author: TJ
 * @date:  2022-11-27
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("===================== Jwt认证");

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StrUtil.isBlank(header)) {
            filterChain.doFilter(request , response);
            return;
        }

        header = header.trim();
        AssertUtil.isTrue(header.startsWith("Bearer"), "======== 认证信息不合法");

        String token = StrUtil.subSuf(header, 7);
        Claims claims = TokenUtil.parse(token);
        String userId = claims.getSubject();

        SmsCodeAuthenticationToken smsCodeAuthenticationToken = new SmsCodeAuthenticationToken(userId, "");
        SecurityContextHolder.getContext().setAuthentication(smsCodeAuthenticationToken);

        filterChain.doFilter(request , response);
    }
}
