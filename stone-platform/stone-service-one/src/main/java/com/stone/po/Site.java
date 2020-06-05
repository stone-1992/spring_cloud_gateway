package com.stone.po;

import com.baomidou.mybatisplus.annotation.*;
import com.zhcx.business.common.datasource.mp.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author 石宏利
 * @description 站点数据库表实体对象
 * @date 2020/5/18 10:29
 */
@TableName("bp_site")
@Data
@EqualsAndHashCode(callSuper = true)
public class Site extends BaseEntity {

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 企业id
     */
    @TableField(value = "corp_id")
    private Long corpId;

    /**
     * 站点编码
     */
    @TableField(value = "site_code")
    private String siteCode;

    /**
     * 站点名称
     */
    @TableField(value = "site_name")
    private String siteName;

    /**
     * 站点类型 对应字典type:site_type;1-首末站,2-中途站,3-混合站
     */
    /*@TableField(value = "site_type")
    private String siteType;*/

    /**
     * 站台类型 对应字典type:site_station_type;1-直线式,2-港湾式
     */
    @TableField(value = "site_station_type")
    private String siteStationType;

    /**
     * 所属行政区划code 存储格式：省code,市code,区code
     */
    @TableField(value = "area_code")
    private String areaCode;

    /**
     * 所属行政区划名称
     */
    @TableField(value = "area")
    private String area;

    /**
     * 经度
     */
    @TableField(value = "site_lng")
    private BigDecimal siteLng;

    /**
     * 纬度
     */
    @TableField(value = "site_lat")
    private BigDecimal siteLat;

    /**
     * 半径
     */
    @TableField(value = "site_radius")
    private BigDecimal siteRadius;

    /**
     * 方向角
     */
    @TableField(value = "site_azimuth")
    private BigDecimal siteAzimuth;

    /**
     * 方向 对应字典type:site_direction;1-由东往西,2-由西往东,3-由北往南,4-由南往北
     */
    @TableField(value = "site_direction")
    private String siteDirection;

    /**
     * 地址
     */
    @TableField(value = "site_addr")
    private String siteAddr;

    /**
     * 站点用途 对应字典type:site_purpose;1-公交换乘,2-地铁换乘,3-城铁换乘,4-高铁换乘
     */
    @TableField(value = "site_purpose")
    private String sitePurpose;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 逻辑删除字段
     */
    @TableLogic
    @TableField(value = "logic_del")
    private Integer logicDel;

}
