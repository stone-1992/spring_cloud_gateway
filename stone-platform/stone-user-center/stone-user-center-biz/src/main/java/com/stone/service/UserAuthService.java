package com.stone.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.common.datasource.mp.IServiceExpander;
import com.stone.vo.UserAuth;

import java.util.List;

/**
 * 用户接口类
 * @author stone
 */
public interface UserAuthService extends IServiceExpander<UserAuth> {

    /**
     * 新增用户信息
     * @param userAuth
     * @return
     */
    public boolean addUserAuth(UserAuth userAuth);

    /**
     * 根据ID删除用户信息
     * @param id
     * @return
     */
    public boolean deleteById(Long id);

    /**
     * 更新用户信息
     * @param userAuth
     * @return
     */
    public boolean updateUserAuth(UserAuth userAuth);

    /**
     * 查询用户信息集合
     * @param userAuth
     * @return
     */
    public List<UserAuth> selectUserAuthList(UserAuth userAuth);

    /**
     * 根据账号查找用户信息
     * @param accountNo
     * @return
     */
    public UserAuth selectByAccountNo(String accountNo);

    /**
     * 分页查询用户信息
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<UserAuth> selectUserAuthPage(String keyword, Long pageNo, Long pageSize);

    /**
     * 批量删除用户信息
     * @param ids
     * @return
     */
    public boolean deleteUserAuthBatch(List<Long> ids);


}
