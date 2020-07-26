package com.stone.service.impl;

import cn.hutool.core.convert.Convert;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.stone.common.datasource.mp.ServiceExpanderImpl;
import com.stone.entity.po.Site;
import com.stone.entity.vo.SiteVO;
import com.stone.mapper.SiteMapper;
import com.stone.service.SiteService;

import java.util.List;


/**
 * @author 石宏利
 * @description 站点Service Impl
 * @date 2020/5/18 10:29
 */
@Service
public class SiteServiceImpl extends ServiceExpanderImpl<SiteMapper, Site> implements SiteService {

    @Override
    public List<Site> queryList(SiteVO siteVO) {
        Site site = Convert.convert(Site.class, siteVO);
        List<Site> list = super.list(Wrappers.query(site));
        return list;
    }

    @Override
    public boolean addSite(SiteVO siteVO) {
        Site site = Convert.convert(Site.class, siteVO);
        return super.save(site);
    }

    @Override
    public boolean editSite(SiteVO siteVO) {
        UpdateWrapper<Site> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", siteVO.getId());

        Site site = Convert.convert(Site.class, siteVO);
        return super.update(site, updateWrapper);
    }

    @Override
    public boolean deleteSite(Long siteId) {
        return super.removeById(siteId);
    }
}
