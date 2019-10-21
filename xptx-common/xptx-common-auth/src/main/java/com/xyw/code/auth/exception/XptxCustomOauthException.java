package com.xyw.code.auth.exception;

import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description:
 * @Date: Create in 下午9:21 2019/10/14
 * https://blog.csdn.net/qq_31063463/article/details/83752459
 */
public class XptxCustomOauthException extends OAuth2Exception {

    @Getter
    private String errorCode;

    public XptxCustomOauthException(String msg) {
        super(msg);
    }

    public XptxCustomOauthException(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}
