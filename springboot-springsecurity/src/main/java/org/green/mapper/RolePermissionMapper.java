package org.green.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.entity.RolePermission;

import java.util.List;

/**
 * @author : greenv
 * @since : 2024/12/18
 */
@Mapper
public interface RolePermissionMapper {
    /**
     * 通过角色ID列表查询权限角色权限列表
     * @param roleIds 角色IDs
     * @return 角色权限列表
     */
    List<RolePermission> getRolePermissionsByRoleIds(@Param("roleIds") List<Long> roleIds);
}
