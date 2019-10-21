package com.xyw.code.base.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class XptxSystemBaseServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XptxSystemBaseServerApplication.class, args);
    }

}
