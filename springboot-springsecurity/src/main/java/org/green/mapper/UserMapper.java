package org.green.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.green.entity.User;

/**
 * @author : greenv
 * @since : 2024/12/17
 */
@Mapper
public interface UserMapper {

    /**
     * 通过名称获取用户信息
     * @param name 名称
     * @return 用户信息
     */
    User getUserByName(String name);
}
