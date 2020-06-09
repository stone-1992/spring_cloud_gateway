package com.stone.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.entity.po.Site;
import com.zhcx.business.common.datasource.mp.BaseMapperExpander;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 石宏利
 * @description 站点Mapper操作
 * @date 2020/5/18 10:29
 */
@Mapper
public interface SiteMapper extends BaseMapperExpander<Site> {

    /**
     * 分页查询站点信息列表，包含绑定的线路
     *
     * @param page 分页信息
     */
    @Select(value = "SELECT" +
            " b.*," +
            " GROUP_CONCAT(b.line_name) as bind_line" +
            " FROM" +
            " (SELECT DISTINCT bs.*,bl.line_name FROM bp_site bs LEFT JOIN bp_line_site bls ON bs.id = bls.site_id LEFT JOIN bp_line bl ON bls.line_id = bl.id" +
            ") b" +
            " ${ew.customSqlSegment}")
    Page<Site> queryPage(Page<Site> page, @Param(Constants.WRAPPER) QueryWrapper<?> wrapper);


    /**
     * 查询站点是否绑定线路
     *
     * @param wrapper
     * @return
     */
    @Select("SELECT COUNT(*) FROM bp_site s JOIN bp_line_site ls ON s.id = ls.site_id ${ew.customSqlSegment}")
    long queryLineStation(@Param(Constants.WRAPPER) QueryWrapper<?> wrapper);

}
