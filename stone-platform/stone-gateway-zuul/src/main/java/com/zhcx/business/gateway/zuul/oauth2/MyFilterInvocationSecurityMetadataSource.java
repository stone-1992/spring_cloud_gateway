//package com.zhcx.business.gateway.zuul.oauth2;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
//import java.util.Collection;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//
//import com.zhcx.business.gateway.zuul.nacos.config.NacosConfigAuthData;
//import com.zhcx.business.gateway.zuul.util.RequestMatchUtils;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * oauth2 Security放行匿名访问接口
// *
// * @title
// * @author 龚进
// * @date 2020年3月30日
// * @version 1.0
// */
//@Slf4j
//public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
//
//	@Autowired
//	private NacosConfigAuthData nacosConfigAuthData;
//
//	@Override
//	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//		FilterInvocation invocation = (FilterInvocation) object;
//		try {
//			// 请求URI 和 请求方式
//			String requestUri = URLDecoder
//					.decode(URLDecoder.decode(invocation.getHttpRequest().getRequestURI(), "utf-8"), "utf-8");
//			String requestType = invocation.getHttpRequest().getMethod();
//			// 从Nacos中获取不要登录即可访问的URL
//			Map<String, String> fixedAuthResValues = nacosConfigAuthData.getAnonymousRequestDatas();
//			if (RequestMatchUtils.matchRequest(requestType, requestUri, fixedAuthResValues)) {
//				return null;
//			}
//		} catch (UnsupportedEncodingException e) {
//			log.error("解析uri失败");
//			log.error(e.getMessage(), e);
//		}
//		return SecurityConfig.createList("ROLE_USER");
//	}
//
//	@Override
//	public Collection<ConfigAttribute> getAllConfigAttributes() {
//		return null;
//	}
//
//	@Override
//	public boolean supports(Class<?> clazz) {
//		return FilterInvocation.class.isAssignableFrom(clazz);
//	}
//}
