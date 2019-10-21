package com.xyw.code.auth.handler;

import cn.hutool.core.util.CharsetUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 自定义Token异常信息
 * @Date: Create in 下午9:28 2019/10/14
 */
public class XptxAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    /**
     * token错误时进入到这里
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Map<String,Object> map = new HashMap<>();
        map.put("code",401);
        map.put("msg","非法访问资源,访问此资源需要完全身份验证");
        map.put("path",request.getServletPath());
        map.put("timestamp",System.currentTimeMillis());
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), map);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}
