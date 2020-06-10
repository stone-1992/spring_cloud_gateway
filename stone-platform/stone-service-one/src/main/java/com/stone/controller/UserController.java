package com.stone.controller;


import com.stone.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.exption.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Api(tags = "获取用户信息")
@RequestMapping("/user")
@RestController
public class UserController {

    @ApiOperation(value = "查询用户信息")
    @GetMapping("getone")
    public R<String> getUser() {
        return R.ok("this is user : service-one1 - getone");
    }

    @ApiOperation(value = "测试抛出异常")
    @GetMapping("throw")
    public R<String> testThrow() {
        Random random = new Random(1);
        int randomInt = random.nextInt();
        System.err.println(" randomInt == 1 : " + (randomInt == 1));
        if (randomInt != 1) {
            throw new BusinessException("测试抛出异常");
        }
        return R.ok("测试通过");
    }
}
