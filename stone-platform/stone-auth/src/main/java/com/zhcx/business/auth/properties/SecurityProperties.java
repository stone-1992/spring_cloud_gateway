package com.zhcx.business.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @classname SecurityProperties
 * @description 认证服务器配置
 * @date 2020/3/3 15:58
 * @author xhe
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "zhcx.security")
@RefreshScope
public class SecurityProperties {
    ValidateCodeProperties code = new ValidateCodeProperties();
}
