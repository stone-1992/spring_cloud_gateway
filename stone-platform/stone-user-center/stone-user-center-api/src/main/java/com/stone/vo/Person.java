package com.stone.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.group.AddGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 员工信息数据库实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_person")
public class Person implements Serializable {

    private static final long serialVersionUID = 2543917194612051387L;

    /**
     * 唯一id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 企业ID
     */
    @TableField(value = "corp_id")
    private Long corpId;

    /**
     * 员工姓名
     */
    @TableField(value = "person_name")
    @NotBlank(message = "员工姓名不能为空", groups = { AddGroup.class})
    private String personName;

    /**
     * 员工手机号码
     */
    @TableField(value = "mobile")
    @NotBlank(message = "手机号码不能为空", groups = { AddGroup.class})
    private String mobile;

    /**
     * 员工性别
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 员工卡号
     */
    @TableField(value = "card_number")
    @NotBlank(message = "员工卡号不能为空", groups = { AddGroup.class})
    private String cardNumber;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 名族代码
     */
    @TableField(value = "nation_code")
    private String nationCode;

    /**
     * 籍贯
     */
    @TableField(value = "native_place")
    private String nativePlace;

}
