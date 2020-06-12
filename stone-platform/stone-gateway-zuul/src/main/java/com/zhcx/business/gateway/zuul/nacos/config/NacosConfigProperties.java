//package com.zhcx.business.gateway.zuul.nacos.config;
//
//import java.util.List;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
//import com.google.common.collect.Lists;
//
//import lombok.Data;
//
///**
// * nacos配置中心属性
// *
// * @title
// * @author 龚进
// * @date 2020年3月27日
// * @version 1.0
// */
//@Data
//@ConfigurationProperties(prefix = "spring.cloud.nacos.config")
//public class NacosConfigProperties {
//
//	/**
//	 * nacos服务器地址
//	 */
//	private String serverAddr;
//
//	/**
//	 * nacos命名空间
//	 */
//	private String namespace;
//
//	/**
//	 * nacos组
//	 */
//	private String group;
//
//	/**
//	 * nacos扩展文件配置
//	 */
//	private List<ExtConfig> extConfig = Lists.newArrayList();
//
//	@Data
//	public static class ExtConfig {
//
//		/**
//		 * 扩展配置文件唯一标识
//		 */
//		private String dataId;
//
//		/**
//		 * 扩展配置文件是否实时刷新
//		 */
//		private Boolean refresh;
//	}
//}
