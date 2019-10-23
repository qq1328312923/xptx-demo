package com.xyw.code.core.constant;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 常量
 * @Date: Create in 下午3:52 2019/10/14
 */
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class XptxConstant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图形验证码
     */
    public static final String XPTX_IMAGE_KEY = "XPTX_IMAGE_KEY";

    /**
     * 网关转发请求带的token解析出来的用户数据
     */
    public static String XPTX_CLIENT_TOKEN_USER = "x-client-token-user";
    /**
     * 网关转发请求带的 TODO 添加服务间简单认证
     */
    public static String XPTX_CLIENT_TOKEN = "x-client-token";

    public static final String LOGIN_QQ = "qq";
    public static final String LOGIN_WEIXIN = "weixin";
    public static final String LOGIN_GITEE = "gitee";
    public static final String LOGIN_GITHUB = "github";

}
