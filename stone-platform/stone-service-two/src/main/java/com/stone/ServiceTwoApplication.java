package com.stone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @EnableDiscoveryClient 启动服务注册，alibab Nacos 服务注册
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceTwoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceTwoApplication.class, args);
	}
}
