package com.stone.gateway.zuul.authorizationmodel.impl;

import java.util.LinkedHashMap;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import com.netflix.zuul.context.RequestContext;
import com.stone.gateway.zuul.authorizationmodel.Oauth2AuthorizationModelAuthorityManagementService;

/**
 * client credentials 客户端模式
 * 
 * @title
 * @date 2019年5月23日
 * @version 1.0
 * @author stone
 */
@Service
public class Oauth2ClientCredentialsAuthorityManagementServiceImpl
		implements Oauth2AuthorizationModelAuthorityManagementService {


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

	@SuppressWarnings("unchecked")
	@Override
	public void setCustomHeaderInfo(Long appId, Long corpId) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (null != authentication && authentication instanceof OAuth2Authentication) {
			OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
			LinkedHashMap<String, Object> details = (LinkedHashMap<String, Object>) oAuth2Authentication
					.getUserAuthentication().getDetails();
			LinkedHashMap<String, Object> oauth2Request = (LinkedHashMap<String, Object>) details.get("oauth2Request");
			String clientId = null == oauth2Request.get("clientId") ? "" : oauth2Request.get("clientId").toString();
			RequestContext requestContext = RequestContext.getCurrentContext();
			// 设置客户端id
			requestContext.addZuulRequestHeader("client_id", clientId);
		}
	}

}
