package org.green.entity;

import lombok.Data;

/**
 * @author : greenv
 * @since : 2024/12/17
 */
@Data
public class Permission {
    /**
     * 权限ID
     */
    private Long permissionId;
    /**
     * 权限名称
     */
    private String permissionName;
}
