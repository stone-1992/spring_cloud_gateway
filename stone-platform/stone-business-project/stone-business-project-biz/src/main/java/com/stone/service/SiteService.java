package com.stone.service;

import com.stone.entity.po.Site;
import com.stone.common.datasource.mp.IServiceExpander;
import com.stone.entity.vo.SiteVO;

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
    List<Site> queryList(SiteVO siteVO);

    /**
     * 新增站点
     * @param siteVO
     * @return
     */
    boolean addSite(SiteVO siteVO);

    /**
     * 编辑站点
     * @param siteVO
     * @return
     */
    boolean editSite(SiteVO siteVO);

    /**
     * 删除站点
     * @param siteId
     * @return
     */
    boolean deleteSite(Long siteId);


}
