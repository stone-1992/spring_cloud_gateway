package com.stone.gateway.zuul.authorizationmodel.impl;

import org.springframework.stereotype.Service;

import com.stone.gateway.zuul.authorizationmodel.Oauth2AuthorizationModelAuthorityManagementService;

/**
 * implicit 简化模式
 * 
 * @title
 * @date 2019年5月23日
 * @version 1.0
 * @author stone
 */
@Service
public class Oauth2ImplicitAuthorityManagementServiceImpl
		implements Oauth2AuthorizationModelAuthorityManagementService {

	@Override
	public boolean matchAuthority(String requestType, String requestUri, Long appId, Long corpId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCustomHeaderInfo(Long appId, Long corpId) throws Exception {
		// TODO Auto-generated method stub

	}

}
