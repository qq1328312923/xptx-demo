package com.xyw.code.gateway.filter;

import com.xyw.code.gateway.config.SwaggerResourceConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.io.Serializable;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: swagger ui文档过滤器 现在还不知道干嘛用 妈的
 * @Date: Create in 下午4:04 2019/10/14
 */
@Component
public class SwaggerHeaderFilter extends AbstractGatewayFilterFactory {
    private static final String HEADER_NAME = "X-Forwarded-Prefix";

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            //待会儿打断点测试
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();
            if (!org.springframework.util.StringUtils.endsWithIgnoreCase(path, SwaggerResourceConfig.API_URI)) {
                return chain.filter(exchange);
            }
            String basePath = path.substring(0, path.lastIndexOf(SwaggerResourceConfig.API_URI));
            ServerHttpRequest newRequest = request.mutate().header(HEADER_NAME, basePath).build();
            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
            return chain.filter(newExchange);
        };
    }
}

