package com.stone.token;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 作者 owen
 * @version 创建时间：2018年2月1日 下午9:47:00 类说明
 */
@Component
@SuppressWarnings("all")
public class AuthorizeConfigManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication,AuthorizationContext authorizationContext) {
        return authentication.map(auth -> {

            // TODO 目前都是true
            boolean hasPermission = true;

            ServerWebExchange exchange = authorizationContext.getExchange();
            ServerHttpRequest request = exchange.getRequest();

            if (auth instanceof OAuth2Authentication) {
                OAuth2Authentication athentication = (OAuth2Authentication) auth;
                String clientId = athentication.getOAuth2Request().getClientId();
            }
            return new AuthorizationDecision(hasPermission);
        }).defaultIfEmpty(new AuthorizationDecision(false));
    }

}
