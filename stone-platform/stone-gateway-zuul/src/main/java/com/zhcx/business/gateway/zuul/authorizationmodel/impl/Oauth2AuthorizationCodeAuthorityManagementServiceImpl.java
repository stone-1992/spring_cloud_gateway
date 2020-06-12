package com.zhcx.business.gateway.zuul.authorizationmodel.impl;

import org.springframework.stereotype.Service;

import com.zhcx.business.gateway.zuul.authorizationmodel.Oauth2AuthorizationModelAuthorityManagementService;

/**
 * authorization code 授权码模式
 * 
 * @title
 * @author 龚进
 * @date 2019年5月23日
 * @version 1.0
 */
@Service
public class Oauth2AuthorizationCodeAuthorityManagementServiceImpl
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
