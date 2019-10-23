package com.xyw.code.springgateway.filter;

import cn.hutool.core.util.StrUtil;
import com.xyw.code.core.constant.SecurityConstant;
import com.xyw.code.core.constant.XptxConstant;
import com.xyw.code.core.exception.ValidateCodeException;
import com.xyw.code.redis.util.XptxRedisUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description: 图形验证码过滤器
 * @Date: Create in 上午10:59 2019/10/21
 */
@Slf4j
@Component
public class ImageCodeFilter extends AbstractGatewayFilterFactory {

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String requestUrl = request.getURI().getPath();
            //如果包含登录 则直接放行
            if(requestUrl.contains(SecurityConstant.OAUTH_TOKEN_URL)){
                return   chain.filter(exchange);
            }
            //剩下的情况严重code
            validateCode(request);
            return chain.filter(exchange);
        };
    }

    /**
     * 验证验证码 如果错误 网关报500
     * @param request
     * @SneakyThrows可以隐性的抛出异常  不然后面还要加throws什么
     */
    @SneakyThrows
    private void validateCode(ServerHttpRequest request) {
        MultiValueMap<String, String>  queryParams = request.getQueryParams();
        // 验证码
        String code = queryParams.getFirst("code");
        // 随机标识
        String t = queryParams.getFirst("t");
        // 验证验证码流程
        if (StrUtil.isBlank(code)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        // 从redis中获取之前保存的验证码跟前台传来的验证码进行匹配
        Object kaptcha = XptxRedisUtil.get(XptxConstant.XPTX_IMAGE_KEY + t);
        if (kaptcha == null) {
            throw new ValidateCodeException("验证码已失效");
        }
        if (!code.toLowerCase().equals(kaptcha)) {
            throw new ValidateCodeException("验证码错误");
        }
    }


}
