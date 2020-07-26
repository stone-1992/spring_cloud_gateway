package com.stone.gateway.zuul.config;

import com.stone.gateway.zuul.filter.CorsResponseFilter;
import com.stone.gateway.zuul.response.R;
import com.stone.gateway.zuul.util.ResponseUtils;
import com.stone.gateway.zuul.util.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * oauth2资源服务器配置 EnableResourceServer 开启oauth2资源服务器
 * 
 * @title
 * @date 2020年3月27日
 * @version 1.0
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private CorsResponseFilter corsResponseFilter;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
		// 自定义token失效异常
		resources.authenticationEntryPoint((req, resp, exception) -> {
			ResponseUtils.writeResponse(resp, JacksonUtils.bean2Json(R.fail401()), HttpStatus.UNAUTHORIZED);
		}).accessDeniedHandler((req, resp, exception) -> {
			ResponseUtils.writeResponse(resp, JacksonUtils.bean2Json(R.fail403()), HttpStatus.FORBIDDEN);
		});
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// 放行options请求和跨域请求
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests()
				.requestMatchers(CorsUtils::isCorsRequest).permitAll()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilterBefore(corsResponseFilter, ChannelProcessingFilter.class).exceptionHandling()
				.authenticationEntryPoint((req, resp, exception) -> {
					ResponseUtils.writeResponse(resp, JacksonUtils.bean2Json(R.fail401()), HttpStatus.UNAUTHORIZED);
				}).and().httpBasic();
		// 用于动态配置不需要登录地址的URL，从Nacos中动态配置
		/*http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			@Override
			public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
				fsi.setSecurityMetadataSource(mySecurityMetadataSource());
				fsi.setAccessDecisionManager(myAccessDecisionManager());
				return fsi;
			}
		});*/
	}

	/*@Bean
	public FilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
		MyFilterInvocationSecurityMetadataSource securityMetadataSource = new MyFilterInvocationSecurityMetadataSource();
		return securityMetadataSource;
	}*/

	/*@Bean
	public AccessDecisionManager myAccessDecisionManager() {
		return new MyAccessDecisionManager();
	}*/

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	/**
	 * redisTokenStore
	 * @return
	 */
	@Bean
	public TokenStore tokenStore() {
		RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
		tokenStore.setPrefix("bp:oauth2:");
		tokenStore.setAuthenticationKeyGenerator(new DefaultAuthenticationKeyGenerator() {
			@Override
			public String extractKey(OAuth2Authentication authentication) {
				return super.extractKey(authentication) + ":";
			}
		});
		return tokenStore;
	}
}
