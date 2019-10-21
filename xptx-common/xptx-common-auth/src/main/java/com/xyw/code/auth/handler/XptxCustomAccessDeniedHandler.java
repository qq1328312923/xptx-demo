package com.xyw.code.auth.handler;

import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 没有权限 授权失败时返回信息
 * @Date: Create in 下午9:36 2019/10/14
 */
public class XptxCustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Map<String,Object> map = new HashMap<>();
        map.put("code",401);
        map.put("msg","抱歉，你没有权限，请联系管理员徐一炜");
        map.put("path",request.getServletPath());
        map.put("timestamp",System.currentTimeMillis());
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(JSONObject.toJSONString(map));
    }
}
