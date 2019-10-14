package com.xyw.code.base.server.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xyw.code.base.api.entity.SysUser;
import com.xyw.code.base.server.mapper.SysUserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 字典详情表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-10-12
 */
@Controller
@RequestMapping("/api/sys-dict-item")
@Api(value = "字典详情表 前端控制器")
public class SysDictItemController {

    @Autowired
    private SysUserMapper sysUserMapper;

    @PostMapping("/test")
    @ResponseBody
    @ApiOperation(value = "测试用例",notes = "测试用例")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "500(系统错误)",response = String.class),
            @ApiResponse(code = 200, message = "测试用例",response = String.class)
    })
    public String test(){
        List<SysUser> list = sysUserMapper.selectList(Wrappers.<SysUser>lambdaQuery().select());
        return JSONObject.toJSONString(list);
    }
}
