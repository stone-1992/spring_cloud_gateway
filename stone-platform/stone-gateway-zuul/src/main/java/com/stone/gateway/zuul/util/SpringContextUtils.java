package com.stone.gateway.zuul.util;

import org.springframework.context.ApplicationContext;

/**
 * spring上下文工具类
 * @title 
 * @date 2020年4月1日
 * @version 1.0
 * @author stone
 */
public class SpringContextUtils {
	private static ApplicationContext applicationContext;

	/**
	 * 获取上下文
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 设置上下文
	 * @param applicationContext
	 */
	public static void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextUtils.applicationContext = applicationContext;
	}

	/**
	 * 通过名字获取上下文中的bean
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	/**
	 * 通过类型获取上下文中的bean
	 * @param requiredType
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}
}
