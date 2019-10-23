package com.xyw.code.authclient.service.impl;

import com.xyw.code.authclient.provider.AuthProvider;
import com.xyw.code.authclient.service.IAuthService;
import com.xyw.code.core.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Service
public class AuthService implements IAuthService {

    @Autowired
    private AuthProvider authProvider;

    /**
     * Authorization认证开头是"bearer "
     */
    private static final int BEARER_BEGIN_INDEX = 7;

    /**
     * jwt token 密钥，主要用于token解析，签名验证
     */
//    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey = "123";

    /**
     * 不需要网关签权的url配置(/oauth,/open)
     * 默认/oauth开头是不需要的
     */
//    @Value("${Valuegate.ignore.authentication.startWith}")
    private String ignoreUrls = "/oauth,/open";
    /**
     * jwt验签
     */
    private MacSigner verifier;

    @Override
    public R<Boolean> authenticate(String token, String url, String method) {
        return authProvider.auth(token, url, method);
    }

    @Override
    public boolean ignoreAuthentication(String url) {
        return Stream.of(this.ignoreUrls.split(",")).anyMatch(ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
    }

    @Override
    public boolean hasPermission(R authResult) {
        log.debug("签权结果:", authResult);
        return authResult.isSuccess() && (boolean) authResult.getData();
    }

    /**
     * 主要的入口方法
     * @param token
     * @param url
     * @param method
     * @return
     */
    @Override
    public boolean hasPermission(String token, String url, String method) {
        //token是否有效
        if (invalidJwtAccessToken(token)) {
            return Boolean.FALSE;
        }
        //从认证服务获取是否有权限
        boolean hasPermission =  hasPermission(authenticate(token, url, method));
        return hasPermission;
    }

    @Override
    public boolean invalidJwtAccessToken(String token) {
        verifier = Optional.ofNullable(verifier).orElse(new MacSigner(signingKey));
        //是否无效true表示无效
        boolean invalid = Boolean.TRUE;
        try {
            Jwt jwt = getJwt(token);
            jwt.verifySignature(verifier);
            invalid = Boolean.FALSE;
            //如果要解析里面的数据的话
            //JSONObject.parseObject(jwt.getClaims());
        } catch (InvalidSignatureException | IllegalArgumentException ex) {
            log.warn("user token has expired or signature error ");
        }
        return invalid;
    }

    @Override
    public Jwt getJwt(String token) {
        return JwtHelper.decode(StringUtils.substring(token, BEARER_BEGIN_INDEX));
    }
}
