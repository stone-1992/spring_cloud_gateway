package com.stone.auth.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.group.AddGroup;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 菜单信息数据库实体类
 * @author Stone
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 2543917194612051386L;

    /**
     * 唯一id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    @TableField(value = "name")
    @NotBlank(message = "菜单不能为空", groups = { AddGroup.class})
    private String name;

    /**
     * 菜单编码
     */
    @TableField(value = "code")
    @NotBlank(message = "菜单编码不能为空", groups = { AddGroup.class})
    private String code;

    /**
     * 上级ID
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 跳转url
     */
    @TableField(value = "url")
    private String url;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 层级
     */
    @TableField(value = "layer")
    private Integer layer;

    /**
     * 状态
     */
    @TableField(value = "status")
    private String status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;


}
