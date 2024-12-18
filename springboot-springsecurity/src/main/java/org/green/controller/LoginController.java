package org.green.controller;


import cn.hutool.json.JSONUtil;
import org.green.service.MyUserDetails;
import org.green.utils.JwtUtils;
import org.green.utils.RedisClient;
import org.green.vo.ResultVO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author : greenv
 * @since : 2024/12/17
 */
@RestController
public class LoginController {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisClient redisClient;

    @PostMapping("/login")
    public ResultVO login(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            if(Objects.isNull(authentication)) {
                //认证失败
                return ResultVO.error("用户名或密码错误");
            }
            //认证成功，保存信息到redis
            MyUserDetails principal = (MyUserDetails) authentication.getPrincipal();
            //将用户信息json化
            String json = JSONUtil.toJsonStr(principal);
            //使用token作为key 过期时间为7天
            String token = JwtUtils.sign(principal.getUsername(), 1000 * 60 * 60 * 24 * 7L);
            //用户信息json化保存到redis
            redisClient.set("login:token:" + token, json,1000*60 * 60 * 24 * 7L);
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            return ResultVO.ok(map);
        }catch (AuthenticationException e) {
            //由AbstractUserDetailsAuthenticationProvider 中的 authenticate方法 抛出 BadCredentialsException
            //经历了前面的认证流程，会从UserDetailsService中拿到真正存在数据库中的用户信息，放到userCache
            //而后此处由前端包装的authentication会和userCache中的用户信息比较，匹配不上就会抛出BadCredentialsException
            //最终捕获为AuthenticationException
            e.printStackTrace();
            return ResultVO.error("用户名或密码错误");
        }
    }
}
