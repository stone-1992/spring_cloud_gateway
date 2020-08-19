package com.stone.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.core.util.PageBean;
import com.stone.core.util.R;
import com.stone.core.util.TreeUtils;
import com.stone.core.validator.ValidatorUtils;
import com.stone.service.MenuService;
import com.stone.vo.Menu;
import com.stone.vo.MenuQuery;
import com.stone.vo.MenuTree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.group.AddGroup;
import model.group.UpdateGroup;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单信息Controller
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单信息接口")
public class MenuController {

    @Reference
    private MenuService menuService;

    @PostMapping("addMenu")
    @ApiOperation("新增菜单信息")
    public R<Boolean> addMenu(@RequestBody Menu menu) {
        // 校验参数
        ValidatorUtils.validateEntity(menu, AddGroup.class);
        return R.ok(menuService.addMenu(menu));
    }

    @PutMapping("updateMenu")
    @ApiOperation("更新菜单信息")
    public R<Boolean> updateMenu(@RequestBody Menu menu) {
        // 1、校验参数
        ValidatorUtils.validateEntity(menu, UpdateGroup.class);
        return R.ok(menuService.updateMenu(menu));
    }

    @DeleteMapping("deleteMenu")
    @ApiOperation("根据ID删除菜单信息")
    public R<Boolean> deleteMenu(@RequestParam(value = "id") Long id) {
        return R.ok(menuService.deleteByMenuId(id));
    }

    @GetMapping("selectMenuByPage")
    @ApiOperation("分页查询菜单信息")
    public R<List<Menu>> selectMenuByPage(@RequestParam("keyword") String keyword, @RequestParam("pageNo") Long pageNo, @RequestParam("pageSize") Long pageSize) {
        Page<Menu> menuPage = menuService.selectMenuByPage(keyword, pageNo, pageSize);
        PageBean pageBean = PageBean.of(menuPage.getCurrent(), menuPage.getSize(), menuPage.getTotal());
        return R.ok(menuPage.getRecords(), pageBean);
    }

    @DeleteMapping("deleteMenuBath")
    @ApiOperation("批量删菜单信息")
    public R<Boolean> deleteMenuBath(@RequestBody MenuQuery menuQuery) {
        List<Long> ids = menuQuery.getIds();
        return R.ok(menuService.deleteMenuBatch(ids));
    }

    @GetMapping("selectMenuList")
    @ApiOperation("查询所有的菜单")
    public R<List<Menu>> selectMenuList(){
        Menu menu = new Menu();
        return R.ok(menuService.listMenus(menu));
    }

    @GetMapping("selectMenuTree")
    @ApiOperation("获取菜单树")
    public R<List<MenuTree>> selectMenuTree() throws Exception {
        System.err.println("获取菜单树========================================");
        Menu menu = new Menu();
        List<Menu> menus = menuService.listMenus(menu);
        List<MenuTree> menuTrees = Convert.convert(new TypeReference<List<MenuTree>>() {
        }, menus);
        if(CollUtil.isEmpty(menuTrees)){
            return null;
        }
        List<MenuTree> tree = TreeUtils.getTree(menuTrees, "id", "parentId", "children", null);
        return R.ok(tree);
    }
}
