package com.stone.common.dubbo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

/**
 * dubbo配置
 * 
 * @title
 * @date 2020年3月2日
 * @version 1.0
 */
@Data
@ConfigurationProperties("spring.dubbo")
public class DubboProperties {

	/**
	 * 应用名称
	 */
	private String applicationName;

	/**
	 * 注册地址
	 */
	private String registryAddress;

	/**
	 * dubbo端口
	 */
	private String protocolPort;
}
