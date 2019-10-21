package com.xyw.code.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 全局鉴权过滤器 GlobalFilter网关全局过滤器接口
 * @Date: Create in 下午4:24 2019/10/14
 */
@Slf4j
@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {

    // 排除过滤的 uri 地址
    private static final String[] WHITE_LIST =
            {"/*/v2/api-docs", "/auth/oauth/token","/user/register", "/swagger-ui.html",
            "/swagger-resources/**",
            "/*/api-docs",
            "/api/socket/**",
            "/log"};

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //得到请求路径
        String urlPath = request.getPath().toString();
        boolean action = false;
        for(String url:WHITE_LIST){
            if(antPathMatcher.match(url,urlPath)){
                action = true;
                break;
            }
        }
        //如果路径匹配  则直接放过过滤
        if(action){
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isEmpty(token)){
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");

            InetSocketAddress inetSocketAddress = request.getRemoteAddress();
            //得到请求ip
            String ip = inetSocketAddress.getAddress().getHostAddress();
            log.info("非法请求，客户端ip:{}，URL:{}",ip,urlPath);
            JSONObject message = new JSONObject();
            message.put("code",HttpStatus.UNAUTHORIZED);
            message.put("msg","非法请求");
            byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
            //nio的wrap操作
            DataBuffer buffer = response.bufferFactory().wrap(bits);

            return response.writeWith(Mono.just(buffer));
        }
        ServerHttpRequest authorization = request.mutate().headers(httpHeaders -> {
            httpHeaders.add("Authorization", token);
        }).build();
        ServerWebExchange serverWebExchange = exchange.mutate().request(authorization).build();
        return chain.filter(serverWebExchange);
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
