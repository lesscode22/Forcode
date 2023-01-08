package com.forcode.auth.detail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

/**
 * 授权类型(grantType):
 *  授权码模式: authorization_code
 *  密码模式: password
 *  客户端模式: client_credentials
 *  简化模式: implicit
 * ------------------------
 * 令牌刷新: refresh_token
 */
@Slf4j
@Component
public class SysClientService implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails details = new BaseClientDetails("sysClientId",
                "sysResourceId",
                "sysScopesId",
                "password,refresh_token",
                "sysAuthorities",
                "http://localhost:8001/test/getUser");
        details.setClientSecret("$2a$10$SxUZC5RuCNT4uQOe660p9uJMrGh5yvzfEEo1TpKF1tCC9gNerkSWm");
        return details;
    }
}
