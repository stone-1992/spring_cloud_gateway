package com.zhcx.business.auth.support.mobile;

import com.zhcx.business.auth.support.feign.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

/**
 * @classname MobileAuthenticationSecurityConfig
 * @description
 * @date 2020/3/26 15:14
 * @author xhe
 */
@Component
public class MobileAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private RemoteUserService userService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        MobileAuthenticationProvider provider = new MobileAuthenticationProvider(userService);
        http.authenticationProvider(provider);
    }
}
