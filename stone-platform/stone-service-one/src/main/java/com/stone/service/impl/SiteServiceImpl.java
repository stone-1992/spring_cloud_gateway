package com.stone.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stone.mapper.SiteMapper;
import com.stone.po.Site;
import com.stone.service.SiteService;
import com.zhcx.business.common.datasource.mp.ServiceExpanderImpl;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author 石宏利
 * @description 站点Service Impl
 * @date 2020/5/18 10:29
 */
@Service
public class SiteServiceImpl extends ServiceExpanderImpl<SiteMapper, Site> implements SiteService {

    @Override
    public List<Site> queryList() {
        Site site = new Site();
        List<Site> list = super.list(Wrappers.query(site));
        return list;
    }
}
