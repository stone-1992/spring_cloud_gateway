package com.stone.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("分布式事务测试接口")
@RequestMapping("JTA")
@RestController
@Slf4j
public class JTAController {

    @GetMapping("test")
    public String testJTA() {
        return "Hello JTA";
    }
}
