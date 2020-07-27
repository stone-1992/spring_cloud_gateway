package com.stone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * nacos 配置中心
 * @RefreshScope  SpringCloud的原生注解
 */
@RestController
@RefreshScope // 支持Nacos 的动态刷新
public class ConfigClientController {

    @Value(value = "${config.info}")
    private String configInfo;

    @GetMapping(value = "config/info")
    public String configInfo(){
        return "this is the nacos config :　" + configInfo;
    }
}
