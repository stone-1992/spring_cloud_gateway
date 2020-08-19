package com.stone.auth.config;

import com.stone.auth.support.webapp.WebAppAuthenticationSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;

/**
 * @classname WebSecurityConfig
 * @description WebSecurityConfig
 * @author stone
 */
@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsServiceImpl;
    @Autowired
    private WebAppAuthenticationSecurityConfig webAppAuthenticationSecurityConfig;

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .loginPage("/token/login")
                    .loginProcessingUrl("/token/form")
                .and()
                .authorizeRequests()
                    .requestMatchers(CorsUtils::isCorsRequest).permitAll()
                    .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                    .antMatchers("/token/**","/actuator/**","/oauth/**","/validateCode/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .apply(webAppAuthenticationSecurityConfig);
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers(HttpMethod.OPTIONS)
                .antMatchers("/favicon.ico");
    }

    /**
     * 全局用户信息
     */
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl);
    }
}
