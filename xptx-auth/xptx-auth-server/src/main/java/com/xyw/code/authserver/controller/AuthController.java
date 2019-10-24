package com.xyw.code.authserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.xyw.code.auth.service.XptxSecurityUser;
import com.xyw.code.auth.util.JwtUtil;
import com.xyw.code.core.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 认证服务
 * @Date: Create in 上午11:06 2019/10/23
 */
@RestController
@Slf4j
public class AuthController {

    @RequestMapping(method = RequestMethod.POST, value = "/auth/permission")
    public R<Boolean> decide(@RequestHeader(HttpHeaders.AUTHORIZATION)  String authentication
            , @RequestParam(value = "url") String url, @RequestParam(value = "method") String method, HttpServletRequest request) {
        log.info("token:{},url:{},method:{}",authentication,url,method);
        //TODO 这里走逻辑去盘这个用户有没有权限  因为每个controller请求都有 @PreAuthorize("hasAuthority('sys:dict:add')") 所以这里暂时不需要判断权限
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(JSONObject.toJSONString(authentication1));

        JSONObject jsonObject = JwtUtil.decode(authentication);
        System.out.println(jsonObject.toJSONString());

        System.out.println(JSONObject.toJSONString( authentication1.getPrincipal()));
        return R.ok(true);
    }
}
