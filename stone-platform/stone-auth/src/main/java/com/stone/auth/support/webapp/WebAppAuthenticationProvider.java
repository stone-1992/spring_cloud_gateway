package com.stone.auth.support.webapp;

import com.stone.auth.model.LoginUser;
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
        /*try {
            password = URLEncoder.createDefault().encode(password, CharsetUtil.CHARSET_UTF_8);
            password = new String(SecureUtil.rsa(SecurityConstants.RSA_PRIVATE_KEY,SecurityConstants.RSA_PUBLIC_KEY).decrypt(Base64.decode(password), KeyType.PrivateKey));
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException("密码加密方式错误");
        }*/
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, null);
        LoginUser loginUser = (LoginUser) userDetailsServiceImpl.loadUserByUsername(username);
        if(!"admin".equals(username)){
            throw new InternalAuthenticationServiceException("账号或者密码错误");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(username);
        userInfo.setPassword(password);
        authenticationToken.setDetails(userInfo);
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
