package com.forcode.base.spring.security.userdetails.sysuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @description:
 *
 * @author: TJ
 * @date:  2022-11-26
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements UserDetails {

    private SysUserInfo userInfo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return "$2a$10$uX55JfF6M/Ea7CJCk6Zkb.rnE0DK/0ySmmdRV4xIgwnS8KXemC5W2";
    }

    @Override
    public String getUsername() {
        return userInfo.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
