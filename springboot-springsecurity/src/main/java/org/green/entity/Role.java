package org.green.entity;

import lombok.Data;

/**
 * @author : greenv
 * @since : 2024/12/17
 */
@Data
public class Role {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
}
