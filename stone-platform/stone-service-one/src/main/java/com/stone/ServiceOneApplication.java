package com.stone;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.stone.swagger.annotation.EnableZhcxSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableZhcxSwagger2
@EnableDiscoveryClient
@EnableDubbo
public class ServiceOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOneApplication.class, args);
    }
}
