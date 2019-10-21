package com.xyw.code.auth.util;

import com.xyw.code.auth.service.LoginType;
import com.xyw.code.core.constant.XptxConstant;

/**
 * @Classname LoginTypeUtil
 * @Description TODO
 * @Author Created by Lihaodong (alias:小东啊) im.lihaodong@gmail.com
 * @Date 2019-09-16 18:26
 * @Version 1.0
 */
public class LoginTypeUtil {

    public static LoginType getLoginType(String state) {
        if (state.equals(XptxConstant.LOGIN_QQ)) {
            return LoginType.qq;
        } else if (state.equals(XptxConstant.LOGIN_WEIXIN)) {
            return LoginType.weixin;
        } else if (state.equals(XptxConstant.LOGIN_GITEE)) {
            return LoginType.gitee;
        }
        return LoginType.github;
    }
}
