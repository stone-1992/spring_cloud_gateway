package com.stone.gateway.zuul.authorizationmodel;

import com.stone.gateway.zuul.oauth2.AuthUserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * 获取授权模式类
 *
 * @version 1.0
 * @title
 * @date 2019年5月24日
 */
public class Oauth2AuthorizationModelUtils {

    /**
     * 获取授权模式
     *
     * @return
     */
    public static String getAuthorizationModel() {
        String authorizationModel = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication && authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oauth2 = (OAuth2Authentication) authentication;
            authorizationModel = oauth2.getOAuth2Request().getRequestParameters().get("grant_type");
        }
        return authorizationModel;
    }

    /**
     * 获取授权用户信息
     *
     * @param appId  应用id
     * @param corpId 企业id
     * @return
     */
    public static AuthUserInfo getAuthUserInfo(Long appId, Long corpId) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication && authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oauth = (OAuth2Authentication) authentication;
            Authentication userAuthentication = oauth.getUserAuthentication();
            // 用户信息
            // UserInfoDTO userInfoDTO = (UserInfoDTO) userAuthentication.getDetails();

        }
        authUserInfo.setUserId(587L).setUsername("18565601630").setIdentifier("18565601630")
                .setIdentityType(1);
        return authUserInfo;
    }

    /**
     * 获取token值
     *
     * @return
     */
    public static String getToken() {
        String token = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication && authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            token = ((OAuth2AuthenticationDetails) oAuth2Authentication.getDetails()).getTokenValue();
        }
        return token;
    }
}
