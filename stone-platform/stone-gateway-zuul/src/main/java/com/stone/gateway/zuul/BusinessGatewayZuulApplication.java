package com.stone.gateway.zuul;

import com.stone.gateway.zuul.util.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ApplicationContext;

/**
 * 启动类 EnableZuulProxy 开启zuul
 * 
 * @title
 * @date 2020年3月27日
 * @version 1.0
 * @author stone
 */
@EnableZuulProxy
@SpringBootApplication
public class BusinessGatewayZuulApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BusinessGatewayZuulApplication.class, args);
		SpringContextUtils.setApplicationContext(context);
	}
}
