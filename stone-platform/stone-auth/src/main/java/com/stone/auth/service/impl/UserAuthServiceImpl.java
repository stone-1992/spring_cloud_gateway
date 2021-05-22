package com.stone.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.auth.mapper.UserAuthMapper;
import com.stone.auth.model.domain.UserAuth;
import com.stone.auth.service.UserAuthService;
import com.stone.common.datasource.mp.ServiceExpanderImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户实现类
 * @author stone
 */
@Service
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

    @Override
    public Page<UserAuth> selectUserAuthPage(String keyword, Long pageNo, Long pageSize) {
        Page page = new Page(pageNo,pageSize);
        QueryWrapper<UserAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("account_no", keyword);
        Page page1 = super.page(page, queryWrapper);
        return page1;
    }

    @Override
    public boolean deleteUserAuthBatch(List<Long> ids) {
        if(CollUtil.isEmpty(ids)){
            return false;
        }
        return super.removeByIds(ids);
    }
}
