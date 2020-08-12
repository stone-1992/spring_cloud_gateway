package com.stone.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.stone.service.UserAuthService;
import com.stone.vo.UserAuth;
import javax.annotation.Resource;

/**
 * 用户对外提供接口 实现类
 */
@Service
public class UserAuthDubboServiceImpl implements UserAuthDubboService{
    @Resource
    private UserAuthService userAuthService;

    @Override
    public UserAuth selectUserAuthByAccountNo(String accountNo) {
        return userAuthService.selectByAccountNo(accountNo);
    }
}
