package com.stone.config;

import com.stone.handler.ResAccessDeniedHandler;
import com.stone.handler.ResAuthenticationEntryPoint;
import com.stone.handler.ResAuthenticationFailureHandler;
import com.stone.handler.ResAuthenticationSuccessHandler;
import com.stone.token.AuthorizeConfigManager;
import com.stone.token.TokenAuthenticationConverter;
import com.stone.token.TokenAuthenticationManager;
import com.stone.utils.PermitUrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.util.Assert;
import org.springframework.web.server.WebFilter;

import javax.annotation.Resource;

/**
 * 资源服务器UAAClientAutoConfig
 */
@Configuration
@SuppressWarnings("all")
@EnableConfigurationProperties(PermitUrlProperties.class)
public class SecurityConfig {
    @Autowired
    private PermitUrlProperties permitUrlProperties;

    @Autowired
    private TokenStore tokenStore;

    @Resource
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    @Bean
    public RedisTokenStore tokenStore(RedisConnectionFactory connectionFactory) {
        Assert.state(connectionFactory != null, "connectionFactory must be provided");
        RedisTokenStore tokenStore = new RedisTokenStore(connectionFactory);
        tokenStore.setPrefix("bp:");
        return tokenStore;


    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        //认证处理器
        ReactiveAuthenticationManager tokenAuthenticationManager = new TokenAuthenticationManager(tokenStore);
        ResAuthenticationEntryPoint resAuthenticationEntryPoint = new ResAuthenticationEntryPoint();

        ResAccessDeniedHandler resAccessDeniedHandler = new ResAccessDeniedHandler();

        //构建Bearer Token
        //请求参数强制加上 Authorization BEARER token
        http.addFilterAt((WebFilter) (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (request.getQueryParams().getFirst("access_token") != null) {
                exchange.getRequest().mutate().headers(httpHeaders ->
                        httpHeaders.add(
                                "Authorization",
                                OAuth2AccessToken.BEARER_TYPE + " " + request.getQueryParams().getFirst("access_token"))
                );
            }
            return chain.filter(exchange);
        }, SecurityWebFiltersOrder.FIRST);

        //身份认证
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(tokenAuthenticationManager);
        authenticationWebFilter.setAuthenticationFailureHandler(new ResAuthenticationFailureHandler()); //登陆验证失败
        authenticationWebFilter.setAuthenticationSuccessHandler(new ResAuthenticationSuccessHandler()); //认证成功
        //token转换器
        TokenAuthenticationConverter tokenAuthenticationConverter = new TokenAuthenticationConverter();
        tokenAuthenticationConverter.setAllowUriQueryParameter(true);
        authenticationWebFilter.setServerAuthenticationConverter(tokenAuthenticationConverter);


        http.addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION);

//        AuthorizationWebFilter authorizationWebFilter=new AuthorizationWebFilter(delegatingAuthorizationManager); //访问授权
//        http.addFilterAt(authorizationWebFilter, SecurityWebFiltersOrder.FORM_LOGIN);

        ServerHttpSecurity.AuthorizeExchangeSpec authorizeExchange = http.authorizeExchange();

        authorizeExchange.matchers(EndpointRequest.toAnyEndpoint()).permitAll(); //无需进行权限过滤的请求路径
        authorizeExchange.pathMatchers(permitUrlProperties.getIgnored()).permitAll();//无需进行权限过滤的请求路径

        authorizeExchange
                .pathMatchers(HttpMethod.OPTIONS).permitAll()    //option 请求默认放行
                // .anyExchange().access(authorizeConfigManager)    // 应用api权限控制
                .anyExchange().authenticated()                  //token 有效性控制
                .and()
                .exceptionHandling()
                .accessDeniedHandler(resAccessDeniedHandler)
                .authenticationEntryPoint(resAuthenticationEntryPoint)
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .httpBasic().disable()
                .csrf().disable();
        return http.build();
    }
}
