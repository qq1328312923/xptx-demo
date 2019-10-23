package com.xyw.code.springgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//网关自带feign接口  然后还要扫描feign下面的自动注入
@EnableFeignClients({"com.xyw.code.springgateway","com.xyw.code.authclient"})
@ComponentScan({"com.xyw.code.springgateway","com.xyw.code.authclient"})
@SpringBootApplication
public class XptxSpringCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(XptxSpringCloudGatewayApplication.class, args);
	}

}
