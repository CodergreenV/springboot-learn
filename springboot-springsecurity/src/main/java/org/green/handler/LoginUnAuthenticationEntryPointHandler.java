package org.green.handler;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.green.vo.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/** 未认证或认证错误时的处理
 * @author : greenv
 * @since : 2024/12/17
 */
@Component
public class LoginUnAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        ResultVO result = ResultVO.error("用户未认证或登录已过期，请重新登录后再访问");
        //将消息json化
        String json = JSONUtil.toJsonStr(result);
        //送到客户端
        response.getWriter().print(json);
    }
}
