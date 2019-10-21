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




    public static final String LOGIN_QQ = "qq";
    public static final String LOGIN_WEIXIN = "weixin";
    public static final String LOGIN_GITEE = "gitee";
    public static final String LOGIN_GITHUB = "github";

}
