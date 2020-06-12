package com.zhcx.business.gateway.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ApplicationContext;

import com.zhcx.business.gateway.zuul.util.SpringContextUtils;

/**
 * 启动类 EnableZuulProxy 开启zuul
 * 
 * @title
 * @author 龚进
 * @date 2020年3月27日
 * @version 1.0
 */
//@EnableZhcxLog
@EnableZuulProxy
@SpringBootApplication
public class BusinessGatewayZuulApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BusinessGatewayZuulApplication.class, args);
		SpringContextUtils.setApplicationContext(context);
	}
}
