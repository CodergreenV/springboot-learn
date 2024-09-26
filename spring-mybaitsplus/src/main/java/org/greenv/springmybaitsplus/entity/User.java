package org.greenv.springmybaitsplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author : GreenV
 * @Date: 2024-09-24 21:14
 * @Description: user类
 */
@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    @TableField(value = "user_name")
    String userName;
    String password;
    String age;
}
