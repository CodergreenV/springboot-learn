package org.green.handler;

import cn.hutool.json.JSONUtil;
import org.green.utils.RedisClient;
import org.green.vo.ResultVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : greenv
 * @since : 2024/12/18
 */
@Component
public class LogoutStatusSuccessHandler implements LogoutSuccessHandler {
    @Resource
    private RedisClient redisClient;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = request.getHeader("token");
        if(StringUtils.hasText(token)){
            redisClient.del("login:token:" + token);
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        ResultVO result = ResultVO.ok("退出成功");
        String json = JSONUtil.toJsonStr(result);
        response.getWriter().print(json);
    }
}
