package com.zhcx.business.common.dubbo.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * dubbo自动配置类
 * 
 * @title
 * @date 2020年3月3日
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties(DubboProperties.class)
public class DubboAutoConfiguration {

}
