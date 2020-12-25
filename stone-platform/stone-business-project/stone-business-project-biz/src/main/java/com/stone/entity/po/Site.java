package com.stone.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.stone.common.datasource.mp.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 石宏利
 * @description 站点数据库表实体对象
 * @date 2020/5/18 10:29
 */
@TableName("bp_site")
@Data
@EqualsAndHashCode(callSuper = true)
public class Site extends BaseEntity implements Serializable {

    /**
     * 需要实现 Serializable 接口，不然在 dubbo 调用的时候，调不通
     */
    private static final long serialVersionUID = -8233376222920850306L;

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
     * 站点金钱
     */
    @TableField(value = "site_money")
    private BigDecimal siteMoney;

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
