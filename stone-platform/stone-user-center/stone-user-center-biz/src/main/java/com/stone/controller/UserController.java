package com.stone.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stone.core.util.R;
import com.stone.service.UserAuthService;
import com.stone.vo.UserAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息Controller
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户信息接口")
public class UserController {

    @Reference
    private UserAuthService userAuthService;

    @PostMapping("addUserAuth")
    @ApiOperation("新增用户信息")
    public R<Boolean> addUserAuth(@RequestBody UserAuth userAuth) {
        return R.ok(userAuthService.addUserAuth(userAuth));
    }

    @PutMapping("updateUserAuth")
    @ApiOperation("更新用户信息")
    public R<Boolean> updateUserAuth(@RequestBody UserAuth userAuth) {
        return R.ok(userAuthService.updateUserAuth(userAuth));
    }

    @DeleteMapping("deleteUserAuth")
    @ApiOperation("根据ID删除用户信息")
    public R<Boolean> deleteUserAuth(@RequestParam(value = "id") Long id) {
        return R.ok(userAuthService.deleteById(id));
    }

    @GetMapping("selectUserAuthList")
    @ApiOperation("查询用户信息list集合")
    public R<List<UserAuth>> selectUserAuthList(){
        UserAuth userAuth = new UserAuth();
        return R.ok(userAuthService.selectUserAuthList(userAuth));
    }


    @PostMapping("test")
    @ApiOperation("test")
    public R test(@RequestBody UserAuth userAuth){
        System.err.println(userAuth);
        return R.ok();
    }

}
