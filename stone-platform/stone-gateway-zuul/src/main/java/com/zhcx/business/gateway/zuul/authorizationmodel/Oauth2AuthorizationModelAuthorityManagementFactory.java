package com.zhcx.business.gateway.zuul.authorizationmodel;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * 获取授权模式实例工厂类
 * 
 * @title
 * @author 龚进
 * @date 2019年5月23日
 * @version 1.0
 */
@Component
public class Oauth2AuthorizationModelAuthorityManagementFactory {

	@Resource(name = "oauth2PasswordAuthorityManagementServiceImpl")
	private Oauth2AuthorizationModelAuthorityManagementService passwordService;

	@Resource(name = "oauth2ClientCredentialsAuthorityManagementServiceImpl")
	private Oauth2AuthorizationModelAuthorityManagementService clientCredentialsService;

	@Resource(name = "oauth2ImplicitAuthorityManagementServiceImpl")
	private Oauth2AuthorizationModelAuthorityManagementService implicitService;

	@Resource(name = "oauth2AuthorizationCodeAuthorityManagementServiceImpl")
	private Oauth2AuthorizationModelAuthorityManagementService authorizationCodeService;
	
	@Resource(name = "oauth2AnonymousManagementServiceImpl")
	private Oauth2AuthorizationModelAuthorityManagementService authorizationAnonymousService;

	/**
	 * 获取授权模式实例
	 * @param authorizationModel 授权模式
	 * @return
	 */
	public Oauth2AuthorizationModelAuthorityManagementService getInstance(String authorizationModel) {
		if (Oauth2AuthorizationModelEnum.PASSWORD.getType().equals(authorizationModel)) {
			return passwordService;
		} else if (Oauth2AuthorizationModelEnum.CLIENT_CREDENTIALS.getType().equals(authorizationModel)) {
			return clientCredentialsService;
		} else if (Oauth2AuthorizationModelEnum.AUTHORIZATION_CODE.getType().equals(authorizationModel)) {
			return authorizationCodeService;
		} else if (Oauth2AuthorizationModelEnum.IMPLICIT.getType().equals(authorizationModel)) {
			return implicitService;
		} else {
			return authorizationAnonymousService;
		}
	}
}
