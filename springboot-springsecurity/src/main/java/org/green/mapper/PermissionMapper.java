package org.green.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.entity.Permission;

import java.util.List;

/**
 * @author : greenv
 * @since : 2024/12/18
 */
@Mapper
public interface PermissionMapper {
    /**
     * 通过权限ID列表查询权限列表
     * @param permissionIds 权限ID列表
     * @return 权限列表
     */
    List<Permission> batchGetPermissionsByPermissionIds(@Param("permissionIds")List<Long> permissionIds);
}
