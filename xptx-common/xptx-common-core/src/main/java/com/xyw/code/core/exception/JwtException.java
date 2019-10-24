package com.xyw.code.core.exception;

import java.io.Serializable;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: jwt异常信息
 * @Date: Create in 上午10:57 2019/10/24
 */
public class JwtException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public JwtException() {
    }

    public JwtException(String message) {
        super(message);
    }
}
