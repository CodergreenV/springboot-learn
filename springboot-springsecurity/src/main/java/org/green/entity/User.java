package org.green.entity;

import lombok.Data;

/**
 * @author : greenv
 * @since : 2024/12/17
 */
@Data
public class User {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String username;
}
