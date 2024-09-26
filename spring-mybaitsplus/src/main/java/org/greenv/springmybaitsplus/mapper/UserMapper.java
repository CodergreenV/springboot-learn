package org.greenv.springmybaitsplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.greenv.springmybaitsplus.entity.User;

import java.util.Map;

/**
 * @Author : GreenV
 * @Date: 2024-09-24 21:14
 * @Description: mapper
 */
public interface UserMapper extends BaseMapper<User> {
//    // 根据 entity 条件，查询全部记录（并翻页）
//    IPage<T> selectPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
//    // 根据 Wrapper 条件，查询全部记录（并翻页）
//    IPage<Map<String, Object>> selectMapsPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
