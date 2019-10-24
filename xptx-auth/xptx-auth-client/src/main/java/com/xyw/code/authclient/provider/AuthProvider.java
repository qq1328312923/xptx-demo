package com.xyw.code.authclient.provider;

import com.xyw.code.authclient.provider.factory.AuthProviderFallbackFactory;
import com.xyw.code.core.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "xptx-auth-server",fallbackFactory = AuthProviderFallbackFactory.class )
public interface AuthProvider {
    /**
     * 调用签权服务，判断用户是否有权限
     *
     * @param authentication
     * @param url
     * @param method
     * @return <pre>
     * Result:
     * {
     *   code:"200"
     *   mesg:"请求成功"
     *   data: true/false
     * }
     * </pre>
     */
    @PostMapping(value = "/auth/permission")
    R<Boolean> auth(@RequestHeader(HttpHeaders.AUTHORIZATION)  String authentication, @RequestParam("url") String url, @RequestParam("method") String method);
}
