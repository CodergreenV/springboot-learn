package org.green.service;

import org.green.entity.User;
import org.green.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author : greenv
 * @since : 2024/12/17
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    /**
     * 实现数据库中用户密码登录功能
     * @param username the username identifying the user whose data is required.
     * @return 用户详细数据：MyUserDetails 包括权限等信息
     * @throws UsernameNotFoundException 用户不存在
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1. 从数据库获取用户
        User user = userMapper.getUserByName(username);
        //2. 判断用户是否存在
        if(Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //3. 返回用户详细数据
        return new MyUserDetails(user);
    }
}
