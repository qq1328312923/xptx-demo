package com.xyw.code.auth.util;

import com.alibaba.fastjson.JSONObject;
import com.xyw.code.auth.service.XptxSecurityUser;
import com.xyw.code.core.exception.JwtException;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;

import java.util.Optional;

import static java.security.KeyRep.Type.SECRET;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description:
 * @Date: Create in 上午10:54 2019/10/24
 */
public class JwtUtil   {

    /**
     * Authorization认证开头是"bearer "
     */
    private static final int BEARER_BEGIN_INDEX = 7;

    /**
     * jwt token 密钥，主要用于token解析，签名验证
     */
    private static String signingKey = "123";

    /**
     * jwt验签
     */
    private static MacSigner verifier;

    /**
     * 解密token|token自带了解密串，使用了公私对串再进行一次校验
     * @return
     */
    public static JSONObject decode(String token) {
        verifier = Optional.ofNullable(verifier).orElse(new MacSigner(signingKey));
        try{
            token =  StringUtils.substring(token, BEARER_BEGIN_INDEX);
            Jwt jwt = JwtHelper.decodeAndVerify(token,verifier);
            return JSONObject.parseObject(jwt.getClaims());
        }catch (RuntimeException e){
            throw new JwtException("jwtError,校验失败");
        }
    }

    /**
     * 获取用户
     *
     * @param authentication
     * @return PreUser
     * <p>
     */
    private XptxSecurityUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof XptxSecurityUser) {
            return (XptxSecurityUser) principal;
        }
        return null;
    }


    /**
     * 获取Authentication
     */
    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


}
