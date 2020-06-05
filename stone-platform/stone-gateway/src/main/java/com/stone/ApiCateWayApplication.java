package com.stone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiCateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCateWayApplication.class, args);
	}
}
