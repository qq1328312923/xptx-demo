package com.xyw.code.base.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xyw.code.base.api.entity.SysUser;
import com.xyw.code.base.server.mapper.SysUserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description:
 * @Date: Create in 上午11:47 2019/10/14
 */
@Controller
@RequestMapping("/test")
@Api(value = "测试用例")
public class TestController {
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
