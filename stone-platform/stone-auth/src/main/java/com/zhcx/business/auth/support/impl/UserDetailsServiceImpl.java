package com.zhcx.business.auth.support.impl;

import com.zhcx.business.auth.constant.SecurityConstants;
import com.zhcx.business.auth.model.LoginUser;
import com.zhcx.business.auth.po.UserAuth;
import com.zhcx.business.auth.support.feign.RemoteUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * @classname UserDetailsServiceImpl
 * @description
 * @date 2020/3/30 16:32
 * @author xhe
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RemoteUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth userAuth = userService.getUserAuth(username);
        Optional.ofNullable(userAuth)
                .orElseThrow(()-> new InternalAuthenticationServiceException("账号或密码错误"));
        if(!SecurityConstants.ALLOW_LOGIN.equals(userAuth.getStatus())){
            throw new InternalAuthenticationServiceException("账号已被禁止登录");
        }
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(userAuth,loginUser);
        return loginUser;
    }
}
