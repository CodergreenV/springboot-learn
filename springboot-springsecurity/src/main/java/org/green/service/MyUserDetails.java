package org.green.service;

import lombok.Data;
import org.green.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : greenv
 * @since : 2024/12/17
 */
@Data
public class MyUserDetails implements UserDetails {
    private User user;

    //角色名称列表, 和授权相关
    private List<String> roleName;
    //权限名称列表, 和授权相关
    private List<String> permissionName;

    public MyUserDetails(User user, List<String> roleName, List<String> permissionName) {
        this.user = user;
        this.roleName = roleName;
        this.permissionName = permissionName;
    }

    //获取权限列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        //添加角色
        if(!CollectionUtils.isEmpty(roleName)) {
            //将角色设置到GrantedAuthority
            for(String roleName : roleName) {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+roleName));
            }
        }
        if(!CollectionUtils.isEmpty(permissionName)) {
            //将权限设置到GrantedAuthority
            for(String permissionName : permissionName) {
                authorities.add(new SimpleGrantedAuthority(permissionName));
            }
        }
        return authorities;
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
