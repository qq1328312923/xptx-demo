package com.xyw.code.springgateway.handler;

import com.xyw.code.core.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: Hystrix 降级处理 网关请求错误重定向到fallback 再到这里
 * @Date: Create in 下午3:41 2019/10/14
 */
@Slf4j
@Component
public class HystrixFallbackHandler  implements HandlerFunction<ServerResponse> {

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        //得到原始的请求的url
        Optional<Object> originalUris = serverRequest.attribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        //如果这个urls里面有东西
        originalUris.ifPresent(originalUri->log.error("网关执行请求:{}失败，hystrix服务降级处理",originalUri));
        //返回空的response
        return ServerResponse
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(R.error("服务异常,请稍后重试")));
    }
}
