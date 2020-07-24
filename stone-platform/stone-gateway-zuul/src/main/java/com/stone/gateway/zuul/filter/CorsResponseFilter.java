package com.stone.gateway.zuul.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;

/**
 * 跨域处理过滤器
 * 
 * @title
 * @date 2020年3月27日
 * @version 1.0
 */
@Slf4j
public class CorsResponseFilter implements Filter {

	private String allowOrigin;
	private String allowMethods;
	private String allowCredentials;
	private String allowHeaders;
	private String exposeHeaders;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("CorsFilter Init...");
		allowOrigin = "*";
		allowMethods = "GET,POST,PUT,DELETE,OPTIONS";
		allowCredentials = "true";
		allowHeaders = "Origin,X-Requested-With,Content-Type,Accept,Authorization,grey,deviceid,corpId,appId";
		exposeHeaders = "Content-Type,Content-disposition";
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (log.isDebugEnabled()) {
			log.debug("url: " + request.getRequestURL() + ", method: " + request.getMethod());
		}
		// 设置response响应头
		if (Strings.isNullOrEmpty(response.getHeader("Access-Control-Allow-Credentials"))) {
			if (StringUtils.isNotBlank(allowOrigin)) {
				List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
				if (null != allowOriginList && allowOriginList.size() > 0) {
					String currentOrigin = request.getHeader("Origin");
					response.setHeader("Access-Control-Allow-Origin", currentOrigin);
				}
			}
			if (StringUtils.isNotBlank(allowMethods)) {
				response.setHeader("Access-Control-Allow-Methods", allowMethods);
			}
			if (StringUtils.isNotBlank(allowCredentials)) {
				response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
			}
			if (StringUtils.isNotBlank(allowHeaders)) {
				response.setHeader("Access-Control-Allow-Headers", allowHeaders);
			}
			if (StringUtils.isNotBlank(exposeHeaders)) {
				response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
			}
		}

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}

	}

}
