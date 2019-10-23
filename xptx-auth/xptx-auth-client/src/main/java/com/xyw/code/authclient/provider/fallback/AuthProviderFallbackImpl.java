package com.xyw.code.authclient.provider.fallback;

import com.xyw.code.authclient.provider.AuthProvider;
import com.xyw.code.core.utils.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description:
 * @Date: Create in 下午7:13 2019/10/23
 */
@Slf4j
@AllArgsConstructor
public class AuthProviderFallbackImpl implements AuthProvider {

    private Throwable throwable;

    /**
     * 降级统一返回无权限
     *
     * @param authentication
     * @param url
     * @param method
     * @return <pre>
     * Result:
     * {
     *   code:"-1"
     *   mesg:"系统异常"
     * }
     * </pre>
     */
    @Override
    public R<Boolean> auth(String authentication, String url, String method) {
        System.out.println(throwable.getLocalizedMessage());
        return R.ok(false);
    }
}