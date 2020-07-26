package com.stone.auth.properties;

import lombok.Data;

/**
 * @classname ValidateCodeProperties
 * @description 验证码配置
 * @date 2020/3/3 16:18
 */
@Data
public class ValidateCodeProperties {
    /**
     * 设置认证通时不需要验证码的clientId
     */
    private String[] ignoreClientCode = {};
}
