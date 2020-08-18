package com.stone.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.common.datasource.mp.IServiceExpander;
import com.stone.vo.Menu;

import java.util.List;

public interface MenuService extends IServiceExpander<Menu> {

    /**
     * 新增菜单信息
     * @param menu
     * @return
     */
    boolean addMenu(Menu menu);

    /**
     * 根据ID删除菜单信息
     * @param menuId
     * @return
     */
    boolean deleteByMenuId(Long menuId);

    /**
     * 更新菜单信息
     * @param menu
     * @return
     */
    boolean updateMenu(Menu menu);

    /**
     * 分页查询菜单信息
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Menu> selectMenuByPage(String keyword, Long pageNo, Long pageSize);

    /**
     * 根据菜单ID集合，批量删除菜单信息
     * @param menuIds
     * @return
     */
    boolean deleteMenuBatch(List<Long> menuIds);

    /**
     * 查询所有的菜单信息
     * @param menu
     * @return
     */
    List<Menu> listMenus(Menu menu);
}
