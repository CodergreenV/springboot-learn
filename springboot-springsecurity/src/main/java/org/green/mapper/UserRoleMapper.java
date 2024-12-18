package org.green.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.green.entity.UserRole;

import java.util.List;

/**
 * @author : greenv
 * @since : 2024/12/18
 */
@Mapper
public interface UserRoleMapper {
    /**
     * 通过用户id查询角色列表
     * @param userId id
     * @return 角色列表
     */
    List<UserRole> getUserRolesByUserId(Long userId);
}
