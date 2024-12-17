package org.green.entity;

import lombok.Data;

/**
 * @author : greenv
 * @since : 2024/12/17
 */
@Data
public class UserRole {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
}
