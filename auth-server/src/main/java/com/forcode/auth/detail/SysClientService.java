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
 */
@Slf4j
@Component
public class SysClientService implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return new BaseClientDetails("sysClientId",
                "sysResourceId",
                "sysScopesId",
                "authorization_code",
                "sysAuthorities");
    }
}
