//package com.zhcx.business.gateway.zuul.nacos.config;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import java.util.concurrent.Executor;
//import java.util.stream.Collectors;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//
//import com.alibaba.nacos.api.NacosFactory;
//import com.alibaba.nacos.api.PropertyKeyConst;
//import com.alibaba.nacos.api.config.ConfigService;
//import com.alibaba.nacos.api.config.listener.Listener;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.google.common.collect.Maps;
//import com.zhcx.business.gateway.zuul.nacos.config.NacosConfigProperties.ExtConfig;
//
///**
// * nacos配置中心自动配置
// *
// * @title
// * @date 2020年3月27日
// * @version 1.0
// */
//@Configuration
//@ConditionalOnClass({ NacosConfigAuthData.class })
//@EnableConfigurationProperties(NacosConfigProperties.class)
//public class NacosConfigAutoConfiguration {
//
//	private final NacosConfigAuthData nacosConfigAuthData;
//
//	public NacosConfigAutoConfiguration(NacosConfigProperties nacosConfigProperties,
//			NacosConfigAuthData nacosConfigAuthData) throws NacosException {
//		this.nacosConfigAuthData = nacosConfigAuthData;
//		Properties properties = new Properties();
//		properties.put(PropertyKeyConst.SERVER_ADDR, nacosConfigProperties.getServerAddr());
//		properties.put(PropertyKeyConst.NAMESPACE, nacosConfigProperties.getNamespace());
//		ConfigService configService = NacosFactory.createConfigService(properties);
//		List<ExtConfig> extConfigs = nacosConfigProperties.getExtConfig();
//		if (!extConfigs.isEmpty()) {
//			List<String> dataIds = extConfigs.stream().map(extConfig -> extConfig.getDataId())
//					.collect(Collectors.toList());
//			// 初始化读取配置文件
//			initConfigData(configService, dataIds, nacosConfigProperties.getGroup());
//			// 监听配置文件更改
//			listenerConfigData(configService, dataIds, nacosConfigProperties.getGroup());
//		}
//	}
//
//	/**
//	 * 初始化读取配置文件数据
//	 *
//	 * @param configService
//	 * @param dataId
//	 * @param group
//	 * @throws NacosException
//	 */
//	private void initConfigData(ConfigService configService, List<String> dataIds, String group) throws NacosException {
//		nacosConfigAuthData.setAnonymousRequestDatas(readConfigData(configService, dataIds.get(1), group));
//		nacosConfigAuthData.setWhiteListRequestDatas(readConfigData(configService, dataIds.get(2), group));
//		nacosConfigAuthData.setClientRequestDatas(readConfigData(configService, dataIds.get(3), group));
//	}
//
//	/**
//	 * 监听配置文件更改
//	 *
//	 * @param configService
//	 * @param dataIds
//	 * @param group
//	 * @throws NacosException
//	 */
//	private void listenerConfigData(ConfigService configService, List<String> dataIds, String group)
//			throws NacosException {
//		updateConfigData(configService, dataIds.get(1), group, 1);
//		updateConfigData(configService, dataIds.get(2), group, 2);
//		updateConfigData(configService, dataIds.get(3), group, 3);
//	}
//
//	/**
//	 * 数据变更
//	 *
//	 * @param configService
//	 * @param dataId
//	 * @param group
//	 * @param index
//	 * @throws NacosException
//	 */
//	private void updateConfigData(ConfigService configService, String dataId, String group, int index)
//			throws NacosException {
//		configService.addListener(dataId, group, new Listener() {
//
//			@Override
//			public Executor getExecutor() {
//				return null;
//			}
//
//			@Override
//			public void receiveConfigInfo(String configInfo) {
//				Map<String, String> resultData = Maps.newHashMap();
//				if (StringUtils.isBlank(configInfo)) {
//					return;
//				}
//				readConfigData(configInfo, resultData);
//				switch (index) {
//				case 1:
//					nacosConfigAuthData.setAnonymousRequestDatas(resultData);
//					break;
//				case 2:
//					nacosConfigAuthData.setWhiteListRequestDatas(resultData);
//					;
//					break;
//				case 3:
//					nacosConfigAuthData.setClientRequestDatas(resultData);
//					;
//					break;
//
//				default:
//					break;
//				}
//			}
//
//		});
//	}
//
//	/**
//	 * 读取配置数据
//	 *
//	 * @param configService
//	 * @param dataId
//	 * @param group
//	 * @return
//	 * @throws NacosException
//	 */
//	private Map<String, String> readConfigData(ConfigService configService, String dataId, String group)
//			throws NacosException {
//		Map<String, String> resultData = Maps.newHashMap();
//		String content = configService.getConfig(dataId, group, 5000);
//		if (StringUtils.isBlank(content)) {
//			return resultData;
//		}
//		return readConfigData(content, resultData);
//	}
//
//	/**
//	 * 读取配置文件数据
//	 *
//	 * @param content
//	 * @param resultData
//	 * @return
//	 */
//	private Map<String, String> readConfigData(String content, Map<String, String> resultData) {
//		String[] array = content.split("\r\n");
//		List<String> datas = Arrays.asList(array);
//		for (String data : datas) {
//			if (StringUtils.isNotBlank(data) && !data.startsWith("#")) {
//				String[] dataArr = data.split("--");
//				if (dataArr.length != 2) {
//					continue;
//				}
//				// 请求方式和请求url不能为空
//				if (StringUtils.isBlank(dataArr[0]) || StringUtils.isBlank(dataArr[1])) {
//					continue;
//				}
//				String[] requestTypes = dataArr[0].split(",");
//				for (String requestType : requestTypes) {
//					resultData.put(requestType + "--" + dataArr[1], requestType);
//				}
//			}
//		}
//		return resultData;
//	}
//}
