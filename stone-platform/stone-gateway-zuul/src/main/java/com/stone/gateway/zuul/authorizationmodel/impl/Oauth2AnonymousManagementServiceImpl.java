package com.stone.gateway.zuul.authorizationmodel.impl;

import org.springframework.stereotype.Service;

import com.stone.gateway.zuul.authorizationmodel.Oauth2AuthorizationModelAuthorityManagementService;

/**
 * 匿名模式
 * 
 * @title
 * @date 2020年4月1日
 * @version 1.0
 */
@Service
public class Oauth2AnonymousManagementServiceImpl implements Oauth2AuthorizationModelAuthorityManagementService {

	@Override
	public boolean matchAuthority(String requestType, String requestUri, Long appId, Long corpId) throws Exception {
		if ("OPTIONS".equalsIgnoreCase(requestType)) {
			return true;
		}
		// 如果uri的最后一个字符串位/,则去掉/
		if (requestUri.endsWith("/")) {
			requestUri = requestUri.substring(0, requestUri.length() - 1);
		}
		return false;
	}

	@Override
	public void setCustomHeaderInfo(Long appId, Long corpId) throws Exception {
		// TODO Auto-generated method stub

	}
}
