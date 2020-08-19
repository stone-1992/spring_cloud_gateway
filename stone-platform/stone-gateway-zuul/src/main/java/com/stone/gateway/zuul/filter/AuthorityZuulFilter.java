package com.stone.gateway.zuul.filter;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.stone.gateway.zuul.authorizationmodel.Oauth2AuthorizationModelAuthorityManagementFactory;
import com.stone.gateway.zuul.authorizationmodel.Oauth2AuthorizationModelAuthorityManagementService;
import com.stone.gateway.zuul.authorizationmodel.Oauth2AuthorizationModelUtils;
import com.stone.gateway.zuul.response.R;
import com.stone.gateway.zuul.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.stone.gateway.zuul.util.JacksonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 权限过滤器,在zuul路由之前
 * 
 * @title
 * @date 2020年4月1日
 * @version 1.0
 * @author stone
 */
@Slf4j
@Component
public class AuthorityZuulFilter extends ZuulFilter {

	@Autowired
	private Oauth2AuthorizationModelAuthorityManagementFactory authorizationModelFoctory;

	@Override
	public Object run() throws ZuulException {
		log.info("权限过滤");
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest httpRequest = context.getRequest();
		// 请求类型
		String requestType = httpRequest.getMethod();
		// 请求uri
		String requestUri = httpRequest.getRequestURI();
		// 服务无响应,返回404
		if ("/error".equals(requestUri)) {
			// 服务无响应404
			try {
				log.info("权限过滤{},{},{}", HttpStatus.NOT_FOUND.value(), requestType, requestUri);
				ResponseUtils.writeResponse(context.getResponse(), JacksonUtils.bean2Json(R.fail404()),
						HttpStatus.NOT_FOUND);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			return null;
		}
		// 获取授权模式
		String authorizationModel = Oauth2AuthorizationModelUtils.getAuthorizationModel();
		Oauth2AuthorizationModelAuthorityManagementService authorizationModelService = authorizationModelFoctory
				.getInstance(authorizationModel);
		try {
			Long appId = Optional.ofNullable(httpRequest.getHeader("appId")).map(id -> Long.parseLong(id.toString()))
					.orElse(null);
			Long corpId = Optional.ofNullable(httpRequest.getHeader("corpId")).map(id -> Long.parseLong(id.toString()))
					.orElse(null);
			// 匹配权限
			boolean isMatch = authorizationModelService.matchAuthority(requestType, requestUri, appId, corpId);
			if (!isMatch) {
				// 权限不够响应403
				try {
					log.info("权限过滤{},{},{}", HttpStatus.FORBIDDEN.value(), requestType, requestUri);
					ResponseUtils.writeResponse(context.getResponse(), JacksonUtils.bean2Json(R.fail403()),
							HttpStatus.FORBIDDEN);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
				return null;
			}
			// 授权成功,设置请求头信息
			authorizationModelService.setCustomHeaderInfo(appId, corpId);
		} catch (Exception e) {
			log.error("匹配权限异常");
			log.error(e.getMessage(), e);
			try {
				log.info("权限过滤{},{},{}", HttpStatus.INTERNAL_SERVER_ERROR.value(), requestType, requestUri);
				ResponseUtils.writeResponse(context.getResponse(), JacksonUtils.bean2Json(R.fail500()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception e1) {
				log.error(e.getMessage(), e);
			}
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
