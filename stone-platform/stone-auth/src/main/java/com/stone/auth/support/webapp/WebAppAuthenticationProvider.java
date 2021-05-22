package com.stone.auth.support.webapp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.stone.auth.model.LoginUser;
import com.stone.auth.model.domain.UserAuth;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.auth.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @classname WebAppAuthenticationProvider
 * @description
 * @author stone
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class WebAppAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsServiceImpl;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();
        if(StringUtils.isBlank(username) || Objects.isNull(password)){
            throw new InternalAuthenticationServiceException("账号密码不能为空");
        }
        // 解密前端密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, null);
        LoginUser loginUser = (LoginUser) userDetailsServiceImpl.loadUserByUsername(username);
        if(Objects.isNull(loginUser)){
            throw new InternalAuthenticationServiceException("账号或者密码错误");
        }
        UserAuth userAuth = BeanUtil.toBean(loginUser, UserAuth.class);

        // 校验密码
        if(!StrUtil.equals(password, userAuth.getAccountPwd())){
            throw new InternalAuthenticationServiceException("账号或者密码错误");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userAuth.getAccountNo());
        userInfo.setPassword(userAuth.getAccountPwd());
        authenticationToken.setDetails(userInfo);
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
