package com.xyw.code.authclient.service;

import com.xyw.code.core.utils.R;
import org.springframework.security.jwt.Jwt;

public interface IAuthService {
    /**
     * 调用签权服务，判断用户是否有权限
     *
     * @param token
     * @param url
     * @param method
     * @return Result
     */
    R authenticate(String token, String url, String method);

    /**
     * 判断url是否在忽略的范围内
     * 只要是配置中的开头，即返回true
     *
     * @param url
     * @return
     */
    boolean ignoreAuthentication(String url);

    /**
     * 查看签权服务器返回结果，有权限返回true
     *
     * @param authResult
     * @return
     */
    boolean hasPermission(R authResult);

    /**
     * 是否无效authentication
     *
     * @param token
     * @return
     */
    boolean invalidJwtAccessToken(String token);

    /**
     * 调用签权服务，判断用户是否有权限
     *
     * @param token
     * @param url
     * @param method
     * @return true/false
     */
    boolean hasPermission(String token, String url, String method);

    /**
     * 从认证信息中提取jwt token 对象
     *
     * @param token 认证信息  Authorization: bearer header.payload.signature
     * @return Jwt对象
     */
    Jwt getJwt(String token);
}
