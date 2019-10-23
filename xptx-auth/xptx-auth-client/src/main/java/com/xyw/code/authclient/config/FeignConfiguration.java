package com.xyw.code.authclient.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @Author: xuyiwei
 * @Email 1328312923@qq.com
 * @Description:
 * @Date: Create in 下午6:44 2019/10/23
 */
@Configuration
public class FeignConfiguration implements Serializable {

    @Bean
    Logger.Level feignLoggerLevel() {
        //这里记录所有，根据实际情况选择合适的日志level
        return Logger.Level.FULL;
    }}
