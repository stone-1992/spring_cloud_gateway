package com.zhcx.business.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * @classname SecurityHandlerConfig
 * @description
 * @date 2020/4/1 17:32
 * @author xhe
 */
@Configuration
public class SecurityHandlerConfig {

    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                ResponseEntity<OAuth2Exception> response = super.translate(e);
                //ResponseEntity.status(oAuth2Exception.getHttpErrorCode());
                //可以拓展字段  目前不做拓展
                //response.getBody().addAdditionalInformation("resp_code", oAuth2Exception.getHttpErrorCode() + "");
                //response.getBody().addAdditionalInformation("resp_msg", oAuth2Exception.getMessage());
                return response;
            }
        };
    }
}
