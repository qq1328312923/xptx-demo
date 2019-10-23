package com.xyw.code.authserver.feign.fallback;

import com.xyw.code.authserver.feign.RemoteUserService;
import com.xyw.code.base.api.dto.UserDetailsInfo;
import com.xyw.code.core.utils.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 远程日志调用实现类
 * @Date: Create in 上午9:52 2019/10/21
 */
@Slf4j
@AllArgsConstructor
public class RemoteLogFallbackImpl implements RemoteUserService {

    private final Throwable throwable;

    @Override
    public R<UserDetailsInfo> info(String username) {
        log.error("feign 调用用户{}出错,信息:{}",username,throwable.getLocalizedMessage());
        return null;
    }
}
