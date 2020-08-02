package com.stone.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stone.common.datasource.mp.ServiceExpanderImpl;
import com.stone.mapper.UserAuthMapper;
import com.stone.service.UserAuthService;
import com.stone.vo.UserAuth;

import java.util.List;

@Service(register = true)
public class UserAuthServiceImpl extends ServiceExpanderImpl<UserAuthMapper, UserAuth> implements UserAuthService {

    @Override
    public boolean addUserAuth(UserAuth userAuth) {
        return super.save(userAuth);
    }

    @Override
    public boolean deleteById(Long id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateUserAuth(UserAuth userAuth) {
        QueryWrapper<UserAuth> updateWrapper = new QueryWrapper<>();
        updateWrapper.eq("id", userAuth.getId());
        return super.update(userAuth, updateWrapper);
    }

    @Override
    public List<UserAuth> selectUserAuthList(UserAuth userAuth) {
        return super.list(Wrappers.query(userAuth));
    }

    @Override
    public UserAuth selectByAccountNo(String accountNo) {
        QueryWrapper<UserAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_no", accountNo);
        return super.getOne(queryWrapper);
    }
}
