package org.green.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.green.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author : greenv
 * @since : 2024/12/17
 */
@AllArgsConstructor
@Data
public class MyUserDetails implements UserDetails {
    private User user;

    //获取权限列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    //获取密码
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //获取用户名
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //账户是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true; //设置不过期
    }

    //账户是否被锁定
    @Override
    public boolean isAccountNonLocked() {
        return true; //设置不锁定
    }

    //凭证（密码）是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true; //设置不过期
    }

    //账户是否可用
    @Override
    public boolean isEnabled() {
        return true; //设置可用
    }
}
