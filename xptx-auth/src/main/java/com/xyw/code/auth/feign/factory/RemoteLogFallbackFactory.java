package com.xyw.code.auth.feign.factory;

import com.xyw.code.auth.feign.RemoteUserService;
import com.xyw.code.auth.feign.fallback.RemoteLogFallbackImpl;
import feign.hystrix.FallbackFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 远程调用日志工厂
 * @Date: Create in 上午9:50 2019/10/21
 */
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteUserService> {

    @Override
    public RemoteUserService create(Throwable throwable) {
        return new RemoteLogFallbackImpl(throwable);
    }
}
