package com.stone.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @classname ResourceServerConfig
 * @description 资源服务器
 * @author stone
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests().antMatchers("/oauth/**", "/login", "/html/**","/user/**","/captcha").permitAll().anyRequest()
                .authenticated().and().httpBasic();
        http.csrf().disable();
    }
}
