package com.forcode.auth.detail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class SysUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("a", "$2a$10$SxUZC5RuCNT4uQOe660p9uJMrGh5yvzfEEo1TpKF1tCC9gNerkSWm", Collections.emptyList());
    }
}
