package org.green.handler;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.green.vo.ResultVO;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/** 权限不足时，报异常
 * @author : greenv
 * @since : 2024/12/18
 */
@Component
public class LoginUnAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResultVO result = ResultVO.error("权限不足，请授权后操作");
        //消息json化
        String json = JSONUtil.toJsonStr(result);
        response.getWriter().write(json);
        //--->需要在config中注册自定义处理器
    }
}
