package com.stone.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.core.util.PageBean;
import com.stone.core.util.R;
import com.stone.core.validator.ValidatorUtils;
import com.stone.service.UserAuthService;
import com.stone.vo.UserAuth;
import com.stone.vo.UserAuthQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.group.AddGroup;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息Controller
 * @author stone
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
        // 校验参数
        ValidatorUtils.validateEntity(userAuth, AddGroup.class);
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
    public R<List<UserAuth>> selectUserAuthList() {
        UserAuth userAuth = new UserAuth();
        return R.ok(userAuthService.selectUserAuthList(userAuth));
    }

    @GetMapping("selectUserAuthPage")
    @ApiOperation("分页查询用户信息")
    public R<List<UserAuth>> selectUserAuthPage(@RequestParam("keyword") String keyword, @RequestParam("pageNo") Long pageNo, @RequestParam("pageSize") Long pageSize) {
        Page<UserAuth> userAuthPage = userAuthService.selectUserAuthPage(keyword, pageNo, pageSize);
        PageBean pageBean = PageBean.of(userAuthPage.getCurrent(), userAuthPage.getSize(), userAuthPage.getTotal());
        return R.ok(userAuthPage.getRecords(), pageBean);
    }

    @DeleteMapping("deleteAuthUserBath")
    @ApiOperation("批量删除用户信息")
    public R<Boolean> deleteAuthUserBath(@RequestBody UserAuthQuery userAuthQuery) {
        List<Long> ids = userAuthQuery.getIds();
        return R.ok(userAuthService.deleteUserAuthBatch(ids));
    }

    @PostMapping("test")
    @ApiOperation("test")
    public R test(@RequestBody UserAuth userAuth) {
        System.err.println(userAuth);
        return R.ok();
    }

}
