package com.zhcx.business.gateway.zuul.authorizationmodel.impl;

import com.netflix.zuul.context.RequestContext;
import com.zhcx.business.gateway.zuul.authorizationmodel.Oauth2AuthorizationModelAuthorityManagementService;
import com.zhcx.business.gateway.zuul.authorizationmodel.Oauth2AuthorizationModelUtils;
import com.zhcx.business.gateway.zuul.oauth2.AuthUserInfo;
import com.zhcx.business.gateway.zuul.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.Optional;

/**
 * password 密码模式
 * 
 * @title
 * @author 龚进
 * @date 2019年5月23日
 * @version 1.0
 */
@Slf4j
@Service
public class Oauth2PasswordAuthorityManagementServiceImpl
		implements Oauth2AuthorizationModelAuthorityManagementService {

	@Resource(name = "redisTemplate")
	private RedisTemplate<String, Object> redisTemplate;


	@SuppressWarnings("unchecked")
	@Override
	public boolean matchAuthority(String requestType, String requestUri, Long appId, Long corpId) throws Exception {
		if ("OPTIONS".equalsIgnoreCase(requestType)) {
			return true;
		}
		// 如果uri的最后一个字符串位/,则去掉/
		if (requestUri.endsWith("/")) {
			requestUri = requestUri.substring(0, requestUri.length() - 1);
		}
		// 获取用户信息
		AuthUserInfo authUserInfo = Oauth2AuthorizationModelUtils.getAuthUserInfo(appId, corpId);
		boolean isPass = Optional.ofNullable(authUserInfo).map(user -> {
			// 如果账号是企业管理员并且已开通此应用,拥有所有权限
			if (authUserInfo.isCorpAdminFlag() && null != authUserInfo.getAppId()) {
				return true;
			}
			return false;
		}).get();
		if (isPass) {
			return true;
		}
		return true;
	}

	@Override
	public void setCustomHeaderInfo(Long appId, Long corpId) throws Exception {
		// 获取用户信息
		AuthUserInfo authUserInfo = Oauth2AuthorizationModelUtils.getAuthUserInfo(appId, corpId);
		Optional.ofNullable(authUserInfo).map(user -> {
			try {
				String value = JacksonUtils.bean2Json(user);
				String encode = URLEncoder.encode(value, "utf-8");
				String token = Oauth2AuthorizationModelUtils.getToken();
				RequestContext requestContext = RequestContext.getCurrentContext();
				// 设置用户信息到请求头
				requestContext.addZuulRequestHeader("Token" + token, encode);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			return user;
		});
	}

}
