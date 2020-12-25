package com.stone.utils;

import com.google.common.collect.Lists;
import com.stone.entity.po.Site;
import com.stone.entity.vo.PersonVO;
import com.stone.entity.vo.SiteVO;

import java.util.List;

/**
 * 模拟生成Bean
 * @author stone
 */
public class CreateBeanUtils {

    /**
     * 获取 Site 对象集合
     *
     * @return
     */
    public static List<Site> getSiteList() {
        List<Site> results = Lists.newArrayList();
        try {
            // 休息5秒
            Thread.sleep(2000);
            Site site = new Site();
            results.add(site);
        } catch (Exception e) {

        }
        return results;
    }

    /**
     * 获取 SiteVo 对象集合
     *
     * @return
     */
    public static List<SiteVO> getSiteVOList() {
        List<SiteVO> results = Lists.newArrayList();
        try {
            // 休息5秒
            Thread.sleep(3000);
            SiteVO site = new SiteVO();
            results.add(site);
        } catch (Exception e) {

        }
        return results;
    }


    /**
     * 获取 PersonVO 对象集合
     *
     * @return
     */
    public static List<PersonVO> getPersonVOList() {
        List<PersonVO> results = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            PersonVO personVO = new PersonVO();
            personVO.setPersonName("张三" + i);
            personVO.setPersonMobile("1856560163" + i);
            personVO.setGender(i % 3);
            results.add(personVO);
        }
        return results;
    }
}
