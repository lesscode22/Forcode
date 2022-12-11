package com.forcode.base.spring.security.extension.sms;

import cn.hutool.core.util.StrUtil;
import com.forcode.base.spring.security.LoginTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 参考默认实现  {@link UsernamePasswordAuthenticationFilter}
 * 
 * @author: TJ
 * @date:  2022-12-01
 **/
@Slf4j
public class SmsCodeAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public static final String FORM_MOBILE_KEY = "mobile";
    public static final String FORM_SMS_CODE_KEY = "smsCode";

    // 指定该过滤器匹配 /sms/login 的post请求
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher(
            LoginTypeEnum.PC_PHONE.getUrl(),
            "POST");

    public SmsCodeAuthenticationProcessingFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("=========================== 进入短信码认证过滤器");
        String mobile = request.getParameter(FORM_MOBILE_KEY);
        mobile = StrUtil.trimToEmpty(mobile);
        String smsCode = request.getParameter(FORM_SMS_CODE_KEY);
        smsCode = StrUtil.trimToEmpty(smsCode);
        SmsCodeAuthenticationToken token = new SmsCodeAuthenticationToken(mobile, smsCode);
        return this.getAuthenticationManager().authenticate(token);
    }
}
