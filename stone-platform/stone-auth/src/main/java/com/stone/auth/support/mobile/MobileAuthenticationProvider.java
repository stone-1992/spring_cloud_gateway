package com.stone.auth.support.mobile;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.stone.auth.po.UserAuth;
import com.stone.auth.support.feign.RemoteUserService;
import com.stone.auth.util.Md5HexPasswordEncoder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.HashSet;
import java.util.Objects;

/**
 * @classname MobileAuthenticationProvider
 * @description 手机号码 + 验证码登录Provider
 * @date 2020/2/25 15:56
 */
@Slf4j
@NoArgsConstructor
public class MobileAuthenticationProvider implements AuthenticationProvider {

    public MobileAuthenticationProvider(RemoteUserService userService) {
        this.userService = userService;
    }

    private RemoteUserService userService;

    /**
     * 公钥
     */
    public static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsOD0oHb007bxLJwjkMZA5N44eZ3xUxm89jnHgpmP8Cy1+5b8gKWOiVMkHB4WAn2eblciwevpGNJH80b9G0ic52Shr5FlzOzek55NN24CSaTFNMjVFHkt6KtdUnGYanvyFVlRzwX119Oii7YQHhNYPSl6Zqk2I+N5gTkmtvpCJRQIDAQAB";

    /**
     * 私钥
     */
    public static final String RSA_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKw4PSgdvTTtvEsnCOQxkDk3jh5nfFTGbz2OceCmY/wLLX7lvyApY6JUyQcHhYCfZ5uVyLB6+kY0kfzRv0bSJznZKGvkWXM7N6Tnk03bgJJpMU0yNUUeS3oq11ScZhqe/IVWVHPBfXX06KLthAeE1g9KXpmqTYj43mBOSa2+kIlFAgMBAAECgYEAihz3/soKyP23OdXuLYMKFLjXAs8fSKVy5Lzt2qnWGXWlYNVDN7m5q5+vMDH6ALLV1lKDBfU57T+PrF9RtKwow8w5i+dik1c4KDhcuB6jxAi6FYcjvgEULZ9xQlUc0v25RVRILgrU7sGSGJGy6RpYPHZOw4PcouPY27DA0JjNh+kCQQDgcxIMPlUJT/Lws4T18/7aqgpNOwCcr8nU0yJe49LRdGiXb67XHYX7Z+mODPAJPXf4BkMmZ/SK+xly/ijBHxbHAkEAxG2m6lIHJbOFlDnX+tXKhrwHyTyuLerCE8xePcko9U4xd+EuxdRGGQrDHZNPLePwR4ueYsZJOzkN0kMWH1zjkwJAQByeayDi6WYM3vRoZljr00n+51CTPC56WHB2wOQStAGsXpVoO8oTG1zzOcA4UGteeVpQlAfdslVHT7GJsqBOwQJAN4qVzt3g3X00gSQV+Pwopw5V2UYKLjuaIwT/hNLzfOFeRXprwk5yf7Q2VY+IrP4tu90zrNRKcq+AQ/rqL3YRdwJAOvCvbIDrfb2TSObpicbldqaSUK5Y2eqr2Trep29Y6ebwDbdLQwg/0GaOoH9Kxi8ekF9HmF/bUQX9Ij/IH5WZXw==";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();
        if(StringUtils.isBlank(username) || Objects.isNull(password)){
            throw new BadCredentialsException("账号或密码错误");
        }
        // 解密前端密码
        try {
            password = password.replaceAll(" ", "+");
            password = new String(SecureUtil.rsa(RSA_PRIVATE_KEY,RSA_PUBLIC_KEY).decrypt(Base64.decode(password), KeyType.PrivateKey));
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException("密码加密方式错误");
        }
        MobileAuthenticationToken auth = new MobileAuthenticationToken(username, password, null);
        UserAuth userAuth = userService.getUserAuth(username);
        if (userAuth == null) {
            throw new UsernameNotFoundException("账号或密码错误");
        }

        if (!Md5HexPasswordEncoder.matches(password, userAuth.getCredential(),userAuth.getSalt())) {
            throw new BadCredentialsException("账号或密码错误");
        }
        User user = new User(userAuth.getIdentifier()
                , userAuth.getCredential()
                , true, true, true, true, new HashSet<GrantedAuthority>());
        auth.setDetails(user);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (MobileAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
