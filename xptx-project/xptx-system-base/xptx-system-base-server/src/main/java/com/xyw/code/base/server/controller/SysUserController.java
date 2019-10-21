package com.xyw.code.base.server.controller;


import com.xyw.code.base.api.entity.SysUser;
import com.xyw.code.base.server.service.ISysUserService;
import com.xyw.code.core.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xuyiwei
 * @since 2019-10-12
 */
@Api(description = "用户模块")
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    /**
     * 主要是提供给授权服务器使用
     *
     * @param username
     * @return
     */
    @ApiOperation("获取用户信息")
    @GetMapping("/info/{username}")
    public R getInfo(@PathVariable String username) {
        SysUser user = userService.findUserByUserIdOrUserNameOrPhone(username);
        if(user==null){
            return R.error(String.format("%s用户为空",username));
        }
        return R.ok(userService.findUserInfo(user));
    }
}
