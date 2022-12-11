package com.forcode.base.spring.security.callback;

import cn.hutool.core.map.MapUtil;
import com.forcode.base.common.Result;
import com.forcode.base.spring.security.LoginTypeEnum;
import com.forcode.base.spring.security.jwt.TokenUtil;
import com.forcode.base.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-12-01
 **/
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("==================== 登录成功, 认证对象: {}", JsonUtil.toJsonPretty(authentication));
        String requestURI = request.getRequestURI();
        String type = LoginTypeEnum.getType(requestURI);
        String token = TokenUtil.create(authentication.getPrincipal().toString(), MapUtil.of(LoginTypeEnum.LOGIN_IDENTITY, type));

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JsonUtil.toJson(Result.ok(token)));
        out.flush();
        out.close();
    }
}
