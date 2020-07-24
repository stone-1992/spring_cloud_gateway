package com.stone.auth.support.webapp;

import com.stone.auth.po.UserAuth;
import com.stone.auth.po.UserInfoDTO;
import com.stone.auth.support.feign.RemoteUserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Objects;

/**
 * @classname WebAppAuthenticationProvider
 * @description
 * @date 2020/2/25 15:56
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class WebAppAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsServiceImpl;
    private RemoteUserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        if (StringUtils.isBlank(username) || Objects.isNull(password)) {
            throw new InternalAuthenticationServiceException("账号密码不能为空");
        }
        // 解密前端密码
        try {
            // RSA 解密
            // password = URLEncoder.createDefault().encode(password, CharsetUtil.CHARSET_UTF_8);
            // password = new String(SecureUtil.rsa(SecurityConstants.RSA_PRIVATE_KEY, SecurityConstants.RSA_PUBLIC_KEY).decrypt(Base64.decode(password), KeyType.PrivateKey));
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException("密码加密方式错误");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, null);
        /*UserAuth userAuth = (UserAuth)userDetailsServiceImpl.loadUserByUsername(username);
        if (!Md5HexPasswordEncoder.matches(password, userAuth.getCredential(),userAuth.getSalt())) {
            throw new InternalAuthenticationServiceException("账号或密码错误");
        }*/

        // 业务代码，验证账号是否可以等
        UserAuth userAuth = new UserAuth();
        userAuth.setIdentifier("18565601630");
        userAuth.setCredential("a123456");
        if (userAuth == null) {
            throw new InternalAuthenticationServiceException("账号或密码错误");
        }

        /**
         * 封装用户详细信息
         */
        UserInfoDTO userInfo = new UserInfoDTO();
        userInfo.setUserAuth(userAuth);
        authenticationToken.setDetails(userInfo);
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
