package com.forcode.base.spring.security.userdetails.sysuser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @description:
 *
 * @author: TJ
 * @date:  2022-11-26
 **/
@Slf4j
@Component
public class SysUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("==============[LoginUserService]");
        return new SysUser(SysUserInfo.getInstance());
    }
}
