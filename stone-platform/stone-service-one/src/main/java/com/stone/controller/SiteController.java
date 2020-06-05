package com.stone.controller;


import com.stone.po.Site;
import com.stone.service.SiteService;
import core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "数据库测试")
@RequestMapping("/dbtest")
@RestController
public class SiteController {

    @Autowired
    private SiteService siteService;

    @ApiOperation(value = "查询数据库测试信息")
    @GetMapping("list")
    public R<List<Site>> getList() {
        List<Site> sites = siteService.queryList();
        return R.ok(sites);
    }


}
