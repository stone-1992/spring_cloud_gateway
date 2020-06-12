//package com.zhcx.business.gateway.zuul.nacos.config;
//
//import java.util.Map;
//
//import org.springframework.context.annotation.Configuration;
//
//import lombok.Data;
//
///**
// * nacos配置中心权限数据
// *
// * @title
// * @author 龚进
// * @date 2020年3月27日
// * @version 1.0
// */
//@Configuration
//@Data
//public class NacosConfigAuthData {
//
//	/**
//	 * 允许匿名访问请求地址集合(未登陆即可访问)
//	 */
//	private Map<String, String> anonymousRequestDatas;
//
//	/**
//	 * 白名单访问请求地址集合(登陆后即可访问)
//	 */
//	private Map<String, String> whiteListRequestDatas;
//
//	/**
//	 * 第三方客户端访问请求地址集合(分配第三方客户端id和密钥后即可访问)
//	 */
//	private Map<String, String> clientRequestDatas;
//}
