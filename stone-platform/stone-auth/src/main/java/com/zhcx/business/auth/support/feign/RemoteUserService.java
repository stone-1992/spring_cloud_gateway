package com.zhcx.business.auth.support.feign;

import com.zhcx.business.auth.po.UserAuth;
import com.zhcx.business.auth.po.UserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @classname RemoteUserService
 * @description 远程调用userService
 * @date 2020/4/20 16:01
 * @author xhe
 */
@FeignClient(contextId = "remoteUserService",name = "business-user-center-biz")
public interface RemoteUserService {
    /**
     * 查询用户全部信息
     * @param userAuth 认证信息
     * @return 用户全部信息
     */
    @PostMapping("/user-center/user/userInfo")
    UserInfoDTO getUserInfo(@RequestBody UserAuth userAuth);

    /**
     * 根据账户查询用户认证信息
     * @param username
     * @return 用户认证信息
     */
    @GetMapping("/user-center/user/userAuth/{username}")
    UserAuth getUserAuth(@PathVariable("username")String username);
}
