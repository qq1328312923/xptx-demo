package com.xyw.code.authserver.feign;

import com.xyw.code.authserver.feign.factory.RemoteLogFallbackFactory;
import com.xyw.code.base.api.dto.UserDetailsInfo;
import com.xyw.code.core.constant.ServiceNameConstants;
import com.xyw.code.core.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 远程调用用户模块
 * @Date: Create in 上午9:30 2019/10/21
 * fallbackFactory能注入注解 能把异常信息带入
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_UMPS_SERVICE,contextId = "remoteUserService",fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteUserService {
    /**
     * 通过用户名查询用户包括角色权限等
     *
     * @param username 用户名
     * @return R
     */
    @GetMapping("/user/info/{username}")
    R<UserDetailsInfo> info(@PathVariable("username") String username);
}
