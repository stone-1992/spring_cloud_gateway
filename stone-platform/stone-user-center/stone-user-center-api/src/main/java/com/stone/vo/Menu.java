package com.stone.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.group.AddGroup;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 菜单信息数据库实体类
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
    // @NotNull(message = "上级组织不能为空", groups = { AddGroup.class})
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
     * 备注
     */
    @TableField(value = "remark")
    private String remark;


}
