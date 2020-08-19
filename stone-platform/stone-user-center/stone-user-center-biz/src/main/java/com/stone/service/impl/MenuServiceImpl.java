package com.stone.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.common.datasource.mp.ServiceExpanderImpl;
import com.stone.mapper.MenuMapper;
import com.stone.service.MenuService;
import com.stone.vo.Menu;

import java.util.List;

/**
 * 菜单 service 实现类
 * @author stone
 */
@Service(register = true)
public class MenuServiceImpl extends ServiceExpanderImpl<MenuMapper, Menu> implements MenuService {


    @Override
    public boolean addMenu(Menu menu) {
        return super.save(menu);
    }

    @Override
    public boolean deleteByMenuId(Long menuId) {
        return super.removeById(menuId);
    }

    @Override
    public boolean updateMenu(Menu menu) {
        UpdateWrapper<Menu> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", menu.getId());
        return super.update(menu, updateWrapper);
    }

    @Override
    public Page<Menu> selectMenuByPage(String keyword, Long pageNo, Long pageSize) {
        Page<Menu> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", keyword);
        return super.page(page, queryWrapper);
    }

    @Override
    public boolean deleteMenuBatch(List<Long> menuIds) {
        if(CollUtil.isEmpty(menuIds)){
            return false;
        }
        return super.removeByIds(menuIds);
    }

    @Override
    public List<Menu> listMenus(Menu menu) {
        return super.list(Wrappers.query(menu));
    }
}
