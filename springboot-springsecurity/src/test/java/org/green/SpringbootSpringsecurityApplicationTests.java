package org.green;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootSpringsecurityApplicationTests {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        String password = passwordEncoder.encode("123456");
        System.out.println(password);
    }

}
