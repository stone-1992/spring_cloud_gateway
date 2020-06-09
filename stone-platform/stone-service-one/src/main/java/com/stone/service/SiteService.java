package com.stone.service;

import com.stone.entity.po.Site;
import com.zhcx.business.common.datasource.mp.IServiceExpander;

import java.util.List;

/**
 * @author 石宏利
 * @description 站点Service
 * @date 2020/5/18 10:29
 */
public interface SiteService extends IServiceExpander<Site> {

    /**
     * 查询所有
     *
     * @return
     */
    List<Site> queryList();

}
