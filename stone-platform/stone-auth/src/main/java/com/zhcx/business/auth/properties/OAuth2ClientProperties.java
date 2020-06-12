package com.zhcx.business.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @classname OAuth2ClientProperties
 * @description
 * @date 2020/4/9 15:17
 * @author xhe
 */
@ConfigurationProperties(prefix = "spring.security.oauth2.client")
public class OAuth2ClientProperties {

    /**
     * OAuth provider details.
     */
    private final Map<String, Provider> provider = new HashMap<>();

    /**
     * OAuth client registrations.
     */
    private final Map<String,Registration> registration = new HashMap<>();

    public Map<String,Provider> getProvider() {
        return this.provider;
    }

    public Map<String,Registration> getRegistration() {
        return this.registration;
    }

    @PostConstruct
    public void validate() {
        this.getRegistration().values().forEach(this::validateRegistration);
    }

    private void validateRegistration(Registration registration) {
        if (!StringUtils.hasText(registration.getClientId())) {
            throw new IllegalStateException("Client id must not be empty.");
        }
    }

    /**
     * A single client registration.
     */
    @Data
    public static class Registration {

        /**
         * Reference to the OAuth 2.0 provider to use. May reference an element from the
         * 'provider' property or used one of the commonly used providers (google, github,
         * facebook, okta).
         */
        private String provider;

        /**
         * Client ID for the registration.
         */
        private String clientId;
        /**
         * 拓展OAuth2ClientProperties token有效期配置
         */
        private int accessTokenValiditySeconds;
        /**
         * 拓展OAuth2ClientProperties refreshToken有效期配置
         */
        private int refreshTokenValiditySeconds;

        /**
         * Client secret of the registration.
         */
        private String clientSecret;

        /**
         * Client authentication method. May be left blank when using a pre-defined
         * provider.
         */
        private String clientAuthenticationMethod;

        /**
         * Authorization grant type. May be left blank when using a pre-defined provider.
         */
        private String authorizationGrantType;

        /**
         * Redirect URI. May be left blank when using a pre-defined provider.
         */
        private String redirectUri;

        /**
         * Authorization scopes. May be left blank when using a pre-defined provider.
         */
        private Set<String> scope;

        /**
         * Client name. May be left blank when using a pre-defined provider.
         */
        private String clientName;
    }

    @Data
    public static class Provider {

        /**
         * Authorization URI for the provider.
         */
        private String authorizationUri;

        /**
         * Token URI for the provider.
         */
        private String tokenUri;

        /**
         * User info URI for the provider.
         */
        private String userInfoUri;

        /**
         * User info authentication method for the provider.
         */
        private String userInfoAuthenticationMethod;

        /**
         * Name of the attribute that will be used to extract the username from the call
         * to 'userInfoUri'.
         */
        private String userNameAttribute;

        /**
         * JWK set URI for the provider.
         */
        private String jwkSetUri;

        /**
         * URI that can either be an OpenID Connect discovery endpoint or an OAuth 2.0
         * Authorization Server Metadata endpoint defined by RFC 8414.
         */
        private String issuerUri;
    }
}
