package org.green.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.green.entity.Role;

import java.util.List;

/**
 * @author : greenv
 * @since : 2024/12/18
 */
@Mapper
public interface RoleMapper {
    /**
     * 通过角色id查询角色
     * @param roleIds 角色ID列表
     * @return 角色列表
     */
    List<Role> batchGetRolesByRoleIds(@Param("roleIds") List<Long> roleIds);
}
