package com.zhcx.business.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stone.core.util.R;
import com.zhcx.business.auth.support.mobile.MobileAuthenticationToken;
import com.zhcx.business.auth.util.AuthUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;

/**
 * @classname OAuth2Controller
 * @description OAuth2控制器
 * @date 2020/3/26 14:29
 * @author xhe
 */
@Slf4j
@RequestMapping("/oauth")
@RestController
public class OAuth2Controller {

    @Autowired
    private ClientDetailsService inMemoryClientDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore tokenStore;

    /**
     * 获取身份凭证
     * @return
     */
    @GetMapping("/principal")
    public Principal principal(Authentication authentication) {
        /**
         * 获取Authentication
         * Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         */
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication)authentication;
        return oAuth2Authentication ;
    }

    /**
     * 删除token
     * @param token
     * @return
     */
    @DeleteMapping("/removeToken/{token}")
    public R removeToken(@PathVariable("token") String token){
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        if(null != oAuth2AccessToken){
            tokenStore.removeAccessToken(oAuth2AccessToken);
            tokenStore.removeRefreshToken(oAuth2AccessToken.getRefreshToken());
        }
        return R.ok();
    }

    @ApiOperation(value = "mobile获取token")
    @PostMapping("/mobile")
    public void getTokenByMobile(
            @ApiParam(required = true, name = "username", value = "手机号码") String mobile,
            @ApiParam(required = true, name = "password", value = "密码") String password,
            HttpServletRequest request, HttpServletResponse response) throws IOException {

        MobileAuthenticationToken token = new MobileAuthenticationToken(mobile, password);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        writerToken(request, response, token, "手机号或密码错误");
    }

    private void writerToken(HttpServletRequest request, HttpServletResponse response, AbstractAuthenticationToken token
            , String badCredentialsMsg) throws IOException {
        try {
            final String[] clientInfos = AuthUtils.extractClient(request);
            String clientId = clientInfos[0];
            String clientSecret = clientInfos[1];

            ClientDetails clientDetails = getClient(clientId, clientSecret);
            TokenRequest tokenRequest = new TokenRequest(new HashMap<>(1), clientId, clientDetails.getScope(), "customer");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
            oAuth2Authentication.setAuthenticated(true);
            response.getWriter().write(objectMapper.writeValueAsString(oAuth2AccessToken));
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            exceptionHandler(response, badCredentialsMsg);
        } catch (Exception e) {
            exceptionHandler(response, e);
        }
    }

    private void exceptionHandler(HttpServletResponse response, Exception e) throws IOException {
        exceptionHandler(response, e.getMessage());
    }

    private void exceptionHandler(HttpServletResponse response, String msg) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(objectMapper.writeValueAsString(R.failed(msg)));
    }

    private ClientDetails getClient(String clientId, String clientSecret) {
        ClientDetails clientDetails = inMemoryClientDetailsService.loadClientByClientId(clientId);

        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在");
        } else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配");
        }
        return clientDetails;
    }
}
