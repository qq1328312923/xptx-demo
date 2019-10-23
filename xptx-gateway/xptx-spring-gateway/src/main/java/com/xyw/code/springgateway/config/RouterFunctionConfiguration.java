package com.xyw.code.springgateway.config;

import com.xyw.code.springgateway.handler.HystrixFallbackHandler;
import com.xyw.code.springgateway.handler.ImageCodeHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 路由配置信息 特殊请求直接在此处理，不进行路由转发
 * @Date: Create in 下午3:54 2019/10/14
 */
@Slf4j
@Component
@AllArgsConstructor
public class RouterFunctionConfiguration {

    /**
     * 不使用AutoWird，因为可以直接用构造器注入，使用了@AllArgsConstructor注解
     */
    private final HystrixFallbackHandler hystrixFallbackHandler;
    private final ImageCodeHandler imageCodeWebHandler;

    /**
     * 这里是cod
     * @return
     */
    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions.route(
                RequestPredicates.path("/fallback")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), hystrixFallbackHandler)
                .andRoute(RequestPredicates.GET("/code")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), imageCodeWebHandler);
    }
}
