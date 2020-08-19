package com.stone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 * @author stone
 */
@SpringBootApplication
public class ApiCateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCateWayApplication.class, args);
	}
}
