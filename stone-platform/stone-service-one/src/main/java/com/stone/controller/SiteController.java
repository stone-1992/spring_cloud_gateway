package com.stone.controller;


import com.stone.entity.po.Site;
import com.stone.entity.vo.SiteVO;
import com.stone.service.SiteService;
import com.stone.utils.CreateBeanUtils;
import core.util.R;
import core.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.group.AddGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Api(tags = "站点管理API接口")
@RequestMapping("/site")
@RestController
public class SiteController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private Executor asyncExecutor;

    @ApiOperation(value = "查询数据库测试信息")
    @GetMapping("list")
    public R<List<Site>> getList() {
        List<Site> sites = siteService.queryList();
        return R.ok(sites);
    }

    @ApiOperation(value = "前端新增，参数校验")
    @PostMapping("add")
    public R add(@RequestBody SiteVO siteVO) {
        // 校验参数
        ValidatorUtils.validateEntity(siteVO, AddGroup.class);

        // 业务逻辑处理

        return R.ok();
    }

    @ApiOperation(value = "异步获取对象信息")
    @GetMapping("async")
    public R<Long> async() {
        long startTime = System.currentTimeMillis();
        // 异步获取数据
        CompletableFuture<List<Site>> siteFure = CompletableFuture.supplyAsync(() -> CreateBeanUtils.getSiteList(), asyncExecutor);
        CompletableFuture<List<SiteVO>> siteVoFure = CompletableFuture.supplyAsync(() -> CreateBeanUtils.getSiteVOList(), asyncExecutor);

        // 等待异步执行完成
        CompletableFuture.allOf(siteFure, siteVoFure);
        List<Site> sites = siteFure.join();
        List<SiteVO> siteVOS = siteVoFure.join();
        long endTime = System.currentTimeMillis();

        // getSiteList() 3秒, getSiteVOList() 2秒, 取最大值 ： 3秒多
        System.err.println("consumer time : " + (endTime - startTime));
        return R.ok((endTime - startTime));
    }

    @ApiOperation(value = "同步获取对象信息")
    @GetMapping("synchronization")
    public R synchronization() {
        long startTime = System.currentTimeMillis();
        List<Site> sites = CreateBeanUtils.getSiteList();
        List<SiteVO> siteVOS = CreateBeanUtils.getSiteVOList();
        long endTime = System.currentTimeMillis();
        // getSiteList() 3秒, getSiteVOList() 2秒, 两时间相加 ： 5秒多
        System.err.println("consumer time : " + (endTime - startTime));
        return R.ok((endTime - startTime));
    }

}
