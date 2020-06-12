package com.zhcx.business.gateway.zuul.authorizationmodel;

import lombok.Getter;

/**
 * oauth2授权模式枚举
 * 
 * @title
 * @author 龚进
 * @date 2019年5月23日
 * @version 1.0
 */
@Getter
public enum Oauth2AuthorizationModelEnum {

	/**
	 * 密码模式
	 */
	PASSWORD("password"),
	/**
	 * 授权码模式
	 */
	AUTHORIZATION_CODE("authorization_code"),
	/**
	 * 简化模式
	 */
	IMPLICIT("implicit"),
	/**
	 * 客户端模式
	 */
	CLIENT_CREDENTIALS("client_credentials"),;
	private String type;

	private Oauth2AuthorizationModelEnum(String type) {
		this.type = type;
	}
}
