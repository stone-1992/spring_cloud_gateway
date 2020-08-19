package com.stone.dubbo;

import com.stone.vo.UserAuth;

/**
 * 用户管理对外提供接口
 * @author Stone
 */
public interface UserAuthDubboService {

    /**
     * 根据用户账号查询用户信息
     * @param accountNo
     * @return
     */
    public UserAuth selectUserAuthByAccountNo(String accountNo);

}
