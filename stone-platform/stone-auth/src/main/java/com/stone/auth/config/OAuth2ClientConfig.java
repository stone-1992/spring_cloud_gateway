package com.stone.auth.config;

import com.stone.auth.properties.OAuth2ClientProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.util.Assert;
import java.util.Map;

/**
 * @classname OAuth2ClientConfig
 * @description 导入OAuth2ClientProperties
 */
@Slf4j
@Import(OAuth2ClientProperties.class)
@Configuration
public class OAuth2ClientConfig {

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public ClientDetailsService inMemoryClientDetailsService() throws Exception {
        Map<String, OAuth2ClientProperties.Registration> registration = oAuth2ClientProperties.getRegistration();
        Assert.notEmpty(registration,"未配置oauth2 client");
        InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder();
        registration.forEach((key,client) -> {
            log.info("已加载 client {} : clientId = {} authorizationGrantType = {} clientSecret = {} scope = {} token有效期 = {}",
                    client.getClientName(),
                    client.getClientId(),
                    client.getAuthorizationGrantType(),
                    client.getClientSecret(),
                    client.getScope(),
                    client.getAccessTokenValiditySeconds());
            builder.withClient(client.getClientId())
                    .authorizedGrantTypes(client.getAuthorizationGrantType().split(","))
                    .secret(passwordEncoder.encode(client.getClientSecret()))
                    .scopes(client.getScope().toArray(new String[client.getScope().size()]))
                    .accessTokenValiditySeconds(client.getAccessTokenValiditySeconds())
                    .refreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds());
        });
        return builder.build();
    }
}
