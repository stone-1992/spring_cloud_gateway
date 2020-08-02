package com.stone.auth.component;

import cn.hutool.core.convert.Convert;
import com.alibaba.dubbo.config.annotation.Reference;
import com.stone.auth.model.LoginUser;
import com.stone.dubbo.UserAuthDubboService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @classname UserDetailsServiceImpl
 * @description
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference
    private UserAuthDubboService userAuthDubboService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Convert.convert(LoginUser.class, userAuthDubboService.selectUserAuthByAccountNo(username));
    }
}
