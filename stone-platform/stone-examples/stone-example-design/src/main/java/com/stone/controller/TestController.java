package com.stone.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "test")
@RestController
public class TestController {

    @GetMapping(value = "")
    @ApiOperation(value = "test", notes = "测试接口")
    public String test() {
        return "this is test controller";
    }
}
