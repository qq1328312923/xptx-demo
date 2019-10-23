package com.xyw.code.authserver.controller;

import com.xyw.code.core.utils.R;
import lombok.extern.slf4j.Slf4j;
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
    public R<Boolean> decide(@RequestHeader("Authorization")String token , @RequestParam String url, @RequestParam String method, HttpServletRequest request) {
       log.info("token:{},url:{},method:{}",token,url,method);
       //TODO 这里走逻辑去盘这个用户有没有权限

       return R.ok(true);
    }
}
