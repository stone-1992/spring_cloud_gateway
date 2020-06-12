package com.zhcx.business.auth.support.webapp;

import com.zhcx.business.auth.support.feign.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

/**
 * @classname WebAppAuthenticationSecurityConfig
 * @description
 * @date 2020/3/31 10:38
 * @author xhe
 */
@Component
public class WebAppAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private UserDetailsService userDetailsServiceImpl;
    @Autowired
    private RemoteUserService userService;

    @Override
    public void configure(HttpSecurity http){
        WebAppAuthenticationProvider provider = new WebAppAuthenticationProvider(userDetailsServiceImpl,userService);
        http.authenticationProvider(provider);
    }
}
