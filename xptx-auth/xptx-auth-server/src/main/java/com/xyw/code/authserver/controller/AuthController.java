package com.xyw.code.authserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 认证服务
 * @Date: Create in 上午11:06 2019/10/23
 */
@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @RequestMapping("/test")
    public String test(){
        System.out.println("进入了认证测试");
        return "111111";
    }
}
