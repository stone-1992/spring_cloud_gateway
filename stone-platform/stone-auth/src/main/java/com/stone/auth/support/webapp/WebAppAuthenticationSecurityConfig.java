package com.stone.auth.support.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

/**
 * @classname WebAppAuthenticationSecurityConfig
 * @description
 * @author stone
 */
@Component
public class WebAppAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Override
    public void configure(HttpSecurity http){
        WebAppAuthenticationProvider provider = new WebAppAuthenticationProvider(userDetailsServiceImpl);
        http.authenticationProvider(provider);
    }
}
