package org.green.entity;

import lombok.Data;

/**
 * @author : greenv
 * @since : 2024/12/17
 */
@Data
public class RolePermission {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 权限ID
     */
    private Long permissionId;
}
