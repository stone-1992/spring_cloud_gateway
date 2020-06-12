package com.zhcx.business.gateway.zuul.authorizationmodel;

/**
 * oauth2授权模式权限管理接口 1.password 密码模式 2.authorization code 授权码模式 3.implicit 简化模式
 * 4.client credentials 客户端模式
 * 
 * @title
 * @author 龚进
 * @date 2019年5月23日
 * @version 1.0
 */
public interface Oauth2AuthorizationModelAuthorityManagementService {

	/**
	 * 匹配权限
	 * @param requestType 请求类型 GET POST UPDATE DELETE
	 * @param requestUri 请求uri地址(相对地址)
	 * @param appId 应用id
	 * @param corpId 企业id
	 * @return
	 * @throws Exception
	 */
	boolean matchAuthority(String requestType, String requestUri,Long appId,Long corpId) throws Exception;

	/**
	 * 设置自定义请求头信息
	 * @param appId
	 * @param corpId
	 * @throws Exception
	 */
	void setCustomHeaderInfo(Long appId,Long corpId) throws Exception;
}
