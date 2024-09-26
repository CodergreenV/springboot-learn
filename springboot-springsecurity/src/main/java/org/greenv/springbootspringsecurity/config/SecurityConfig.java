package org.greenv.springbootspringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @Author : GreenV
 * @Date: 2024-09-26 10:12
 * @Description: 配置类
 */
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain mySecurityFilterChain(HttpSecurity http) throws Exception {
        // 登录相关配置
        http.authorizeRequests()
                //所有请求认证
                .anyRequest()
                // 需要认证
                .authenticated()
                .and()
                // 开启httpBasic认证
                .formLogin()
                .loginPage("/myLogin.html")
                .permitAll()
                .and()
                .csrf()
                .disable();
        return http.build(); // 返回构建的SecurityFilterChain实例
    }

}
