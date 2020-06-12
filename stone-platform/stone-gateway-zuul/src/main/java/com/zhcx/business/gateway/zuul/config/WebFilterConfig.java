package com.zhcx.business.gateway.zuul.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.zhcx.business.gateway.zuul.filter.CorsResponseFilter;

/**
 * web过滤器配置
 * 
 * @title
 * @author 龚进
 * @date 2020年3月27日
 * @version 1.0
 */
@Configuration
public class WebFilterConfig {

	/**
	 * 配置跨域过滤器
	 * 
	 * @return
	 */
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public FilterRegistrationBean<CorsResponseFilter> corsResponseFilterRegister(CorsResponseFilter corsResponseFilter) {
		FilterRegistrationBean<CorsResponseFilter> registration = new FilterRegistrationBean<CorsResponseFilter>();
		registration.setFilter(corsResponseFilter);
		registration.addUrlPatterns("/**");
		registration.setName("corsResponseFilter");
		return registration;
	}
	
	@Bean
	public CorsResponseFilter corsResponseFilterFactory() {
		return new CorsResponseFilter();
	}
}
