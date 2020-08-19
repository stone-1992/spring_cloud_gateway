package com.stone.gateway.zuul.config;

import com.stone.gateway.zuul.filter.CorsResponseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * web过滤器配置
 * 
 * @title
 * @date 2020年3月27日
 * @version 1.0
 * @author stone
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
