package com.xyw.code.authclient.provider.factory;

import com.xyw.code.authclient.provider.AuthProvider;
import com.xyw.code.authclient.provider.fallback.AuthProviderFallbackImpl;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description:
 * @Date: Create in 下午7:16 2019/10/23
 */
@Component
@Slf4j
public class AuthProviderFallbackFactory implements FallbackFactory<AuthProvider> {

    @Override
    public AuthProvider create(Throwable throwable) {
         return new AuthProviderFallbackImpl(throwable);
    }
}