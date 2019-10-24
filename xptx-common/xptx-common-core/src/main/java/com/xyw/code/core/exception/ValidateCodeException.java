package com.xyw.code.core.exception;

import java.io.Serializable;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 验证码错误类
 * @Date: Create in 下午6:30 2019/10/22
 */
public class ValidateCodeException extends BaseException implements Serializable {

    private static final long serialVersionUID = 1L;

    public ValidateCodeException() {
        super();
    }

    public ValidateCodeException(String message) {
        super(message);
    }
}

