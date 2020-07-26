//package com.zhcx.business.gateway.zuul.oauth2;
//
//import java.util.Collection;
//
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//
///**
// * 放行匿名用户
// * @title
// * @date 2020年3月30日
// * @version 1.0
// */
//public class MyAccessDecisionManager implements AccessDecisionManager {
//	@Override
//	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
//			throws AccessDeniedException, InsufficientAuthenticationException {
//		// 获取身份凭证
//		Object principal = authentication.getPrincipal();
//		authentication.getDetails();
//		if (principal != null && !"anonymousUser".equals(principal)) {
//			return;
//		}
//		if (null == configAttributes) {
//			return;
//		}
//		throw new AccessDeniedException("not allow");
//	}
//
//	@Override
//	public boolean supports(ConfigAttribute attribute) {
//		return true;
//	}
//
//	@Override
//	public boolean supports(Class<?> clazz) {
//		return true;
//	}
//
//}
