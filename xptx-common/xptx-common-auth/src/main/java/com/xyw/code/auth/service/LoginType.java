package com.xyw.code.auth.service;

import com.xyw.code.core.constant.XptxConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 登录枚举
 * @Date: Create in 下午9:42 2019/10/14
 */
@Getter
@AllArgsConstructor
public enum LoginType {
    /**
     * 用户密码登录
     */
    normal(1, "用户密码登录"),
    /**
     * 短信密码
     */
    sms(2, "短信密码"),
    /**
     * 社交登录
     */
    qq(3, XptxConstant.LOGIN_QQ),
    weixin(4, XptxConstant.LOGIN_WEIXIN),
    gitee(5, XptxConstant.LOGIN_GITEE),
    github(6, XptxConstant.LOGIN_GITHUB);

    private int type;
    private String description;
}
