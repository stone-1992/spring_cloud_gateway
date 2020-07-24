package com.stone.gateway.zuul.util;

import java.util.List;
import java.util.Map;

import org.springframework.util.AntPathMatcher;

/**
 * 请求匹配工具类
 * 
 * @title
 * @date 2020年4月15日
 * @version 1.0
 */
public class RequestMatchUtils {

	private final static AntPathMatcher antPathMatcher = new AntPathMatcher();

	/**
	 * 匹配请求
	 * 
	 * @param requestType
	 * @param requestUri
	 * @param cacheValues
	 * @return
	 */
	public static boolean matchRequest(String requestType, String requestUri, Map<String, String> cacheValues) {
		String matchStr = requestType + "--" + requestUri;
		if (null != cacheValues) {
			if (cacheValues.containsKey(requestUri)) {
				return true;
			}
			// 路径匹配
			for (Map.Entry<String, String> entry : cacheValues.entrySet()) {
				if (antPathMatcher.match(entry.getKey(), matchStr)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 匹配urls
	 * 
	 * @param requestType
	 * @param requestUri
	 * @param urls
	 * @return
	 */
	public static boolean matchUrl(String requestType, String requestUri, List<String> urls) {
		String matchStr = requestType + "--" + requestUri;
		for (String url : urls) {
			if (antPathMatcher.match(url, matchStr)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 匹配url
	 * @param requestType
	 * @param requestUri
	 * @param url
	 * @return
	 */
	public static boolean matchUrl(String requestType, String requestUri, String url) {
		String matchStr = requestType + "--" + requestUri;
		if (antPathMatcher.match(url, matchStr)) {
			return true;
		}
		return false;
	}
}
