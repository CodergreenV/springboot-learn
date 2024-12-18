package org.green.service;

import org.green.entity.*;
import org.green.mapper.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : greenv
 * @since : 2024/12/17
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Resource
    private PermissionMapper permissionMapper;

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
        //3. 将用户信息送到Security的ThreadLocal中
        List<String> roleNames = new ArrayList<>();
        List<String> permissionNames = new ArrayList<>();
        List<UserRole> userRoles = userRoleMapper.getUserRolesByUserId(user.getUserId());
        if(!CollectionUtils.isEmpty(userRoles)) {
            //roleIds --->通过roleIds可以拿到角色列表和权限列表
            List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            if(!CollectionUtils.isEmpty(roleIds)) {
                //3.1 查询角色信息，并存到roleNames中
                List<Role> roleList = roleMapper.batchGetRolesByRoleIds(roleIds);
                if(!CollectionUtils.isEmpty(roleList)) {
                    List<String> roleNameList = roleList.stream().map(Role::getRoleName).collect(Collectors.toList());
                    roleNames.addAll(roleNameList);
                }
                //3.2 通过角色查询权限信息
                List<RolePermission> rolePermissions = rolePermissionMapper.getRolePermissionsByRoleIds(roleIds);
                if(!CollectionUtils.isEmpty(rolePermissions)) {
                    List<Long> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
                    if(!CollectionUtils.isEmpty(permissionIds)) {
                        List<Permission> permissionList = permissionMapper.batchGetPermissionsByPermissionIds(permissionIds);
                        if(!CollectionUtils.isEmpty(permissionList)) {
                            List<String> permissionNameList = permissionList.stream().map(Permission::getPermissionName).collect(Collectors.toList());
                            permissionNames.addAll(permissionNameList);
                        }
                    }
                }
            }
        }
        //3. 返回用户详细数据
        return new MyUserDetails(user, roleNames, permissionNames);
    }
}
