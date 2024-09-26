package org.greenv.springmybaitsplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.greenv.springmybaitsplus.entity.User;
import org.greenv.springmybaitsplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringMybaitsplusApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void testSelectPage() {
        int current = 2;
        int size = 2;
        IPage<User> page = new Page<>(current,size);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("age", 21);
        userMapper.selectPage(page,wrapper);

        // 获取page的 Records属性 当前页数据
        List<User> records = page.getRecords();
        //总条数
        long total = page.getTotal();
        //总页数
        long pages = page.getPages();
        System.out.println("当前数据总共有:"+total);
        System.out.println("共"+pages+"页");
        System.out.println("当前页数据:"+records);
    }

}
