package org.green.handler;

import cn.hutool.json.JSONUtil;
import io.netty.util.internal.StringUtil;
import org.green.service.MyUserDetails;
import org.green.utils.RedisClient;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/** 自定义过滤器，通过token获取凭证
 * @author : greenv
 * @since : 2024/12/18
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisClient redisClient;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头
        String token = request.getHeader("token");
        if(StringUtils.hasLength(token)) {
            String key = "login:token:" + token;
            String json = redisClient.get(key);
            if(StringUtils.hasLength(json)) {
                //解析用户信息
                MyUserDetails principal = JSONUtil.toBean(json, MyUserDetails.class);
                if(Objects.nonNull(principal)) {
                    //封装凭证
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
                    //将Redis中的信息传送到threadLocal
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }else{
                    SecurityContextHolder.getContext().setAuthentication(null);
                }
            }
        }
        //放行，后续执行SpringSecurity的过滤器
        filterChain.doFilter(request, response);
        //-->去config类中配置我们的自定义过滤器
    }
}
