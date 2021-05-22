package com.stone.auth.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单信息数据库实体类
 * @author stone
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuTree implements Serializable {

    private static final long serialVersionUID = 2543917194612051386L;

    @ApiModelProperty("菜单id")
    private Long id;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("菜单编码")
    private String code;

    @ApiModelProperty("上级ID")
    private Long parentId;

    @ApiModelProperty("跳转url")
    private String url;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("层级")
    private Integer layer;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("子级")
    private List<MenuTree> children;
}
