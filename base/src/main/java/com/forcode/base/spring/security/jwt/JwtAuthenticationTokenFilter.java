package com.forcode.base.spring.security.jwt;

import cn.hutool.core.util.StrUtil;
import com.forcode.base.spring.security.LoginTypeEnum;
import com.forcode.base.spring.security.extension.sms.SmsCodeAuthenticationToken;
import com.forcode.base.utils.AssertUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @description: token校验过滤, 参考 {@link BasicAuthenticationFilter}
 *  优先级会高于自定义的登录认证过滤器
 *
 * @author: TJ
 * @date:  2022-11-27
 **/
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("===================== Jwt认证");
        String url = request.getRequestURI();
        if (LoginTypeEnum.enableUrl(url) || url.startsWith("/open/")) {
            log.info("=========== jwt放行");
            filterChain.doFilter(request , response);
            return;
        }

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StrUtil.isBlank(header)) {
            log.info("============= 缺少token, 跳过");
            filterChain.doFilter(request , response);
            return;
        }

        header = header.trim();
        AssertUtil.isTrue(header.startsWith("Bearer"), "======== 认证信息不合法");

        String token = StrUtil.subSuf(header, 7);
        Claims claims = TokenUtil.parse(token);
        String userName = claims.getSubject();
        String loginIdentity = claims.get(LoginTypeEnum.LOGIN_IDENTITY).toString();
        AssertUtil.notEmpty(loginIdentity, "============== 缺少登录标识");
        AssertUtil.isTrue(LoginTypeEnum.enableType(loginIdentity), "========= 非法登录请求");

        Authentication authentication;
        if (LoginTypeEnum.PC_USERNAME.getLoginType().equals(loginIdentity)) {
            authentication = new UsernamePasswordAuthenticationToken(userName, "", Collections.emptyList());
        } else {
            authentication = new SmsCodeAuthenticationToken(userName, "", Collections.emptyList());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request , response);
    }
}
