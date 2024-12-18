package org.green.config;

import jakarta.annotation.Resource;
import org.green.handler.JwtAuthenticationTokenFilter;
import org.green.handler.LoginUnAccessDeniedHandler;
import org.green.handler.LoginUnAuthenticationEntryPointHandler;
import org.green.handler.LogoutStatusSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author : greenv
 * @since : 2024/12/17
 */
@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    /**
     * 登录认证自定义处理器
     */
    @Resource
    private LoginUnAuthenticationEntryPointHandler loginUnAuthenticationEntryPointHandler;
    /**
     * 自定义过滤器
     */
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationFilter;
    /**
     *  权限不足自定义处理器
     */
    @Resource
    private LoginUnAccessDeniedHandler loginUnAccessDeniedHandler;
    /**
     * 注销处理器
     */
    @Resource
    private LogoutStatusSuccessHandler logoutStatusSuccessHandler;

    /**
     * 密码加密
     * @return 密码加密类
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置认证管理器
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * SpringSecurity过滤器
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*http.csrf().disable() //防止csrf攻击
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();
        //注册自定义过滤器
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        //注册自定义处理器
        http.exceptionHandling().authenticationEntryPoint(loginUnAuthenticationEntryPointHandler);
        //注册自定义过滤器
        http.exceptionHandling().accessDeniedHandler(loginUnAccessDeniedHandler);
        //注册自定义处理器
        http.logout().logoutSuccessHandler(logoutStatusSuccessHandler);
        return http.build();*/

        /**
         * 版本更新
         */
        //防止跨站请求
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement((sessionManagement) ->{
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        //设置规则
        http.authorizeHttpRequests(authorizeRequests->{authorizeRequests.requestMatchers("/login","/test1").permitAll().anyRequest().authenticated();});
//        http.authorizeRequests().requestMatchers("/login","test1");
        //注册自定义过滤器
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        //注册自定义处理器
        http.exceptionHandling((exceptionHandling) -> exceptionHandling.authenticationEntryPoint(loginUnAuthenticationEntryPointHandler));
        http.exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedHandler(loginUnAccessDeniedHandler));
        http.logout((logout) -> logout.logoutSuccessHandler(logoutStatusSuccessHandler));
        return http.build();
    }
}
