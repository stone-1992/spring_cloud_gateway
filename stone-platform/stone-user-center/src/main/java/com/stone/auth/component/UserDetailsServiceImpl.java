package com.stone.auth.component;

import cn.hutool.core.convert.Convert;
import com.stone.auth.model.LoginUser;
import com.stone.auth.service.UserAuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author stone
 * @classname UserDetailsServiceImpl
 * @description
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Resource
    private UserAuthService userAuthService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Convert.convert(LoginUser.class, userAuthService.selectByAccountNo(username));
    }
}
