package com.zhcx.business.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @classname AuthServerApplication
 * @description 认证服务器启动类
 * @date 2019/12/12 15:41
 * @author xhe
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class AuthServerApplication {

    public static void main(String[] args){
        SpringApplication.run(AuthServerApplication.class,args);
    }
}
