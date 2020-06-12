package com.zhcx.business.auth.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户认证表(BpUserAuth)实体类
 *
 * @author xhe
 * @since 2020-03-11 11:00:01
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "bp_user_auth")
@ApiModel(value = "用户账户信息")
public class UserAuth implements Serializable {
    private static final long serialVersionUID = 865599305142019139L;
    /**
    * 主键id 主键id
    */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    /**
    * 平台用户id
    */
    @ApiModelProperty(value = "平台用户唯一id")
    private Long userId;
    /**
    * 身份唯一标识，如：登录账号、邮箱地址、手机号码、QQ号码、微信号、微博号；
    */
    @ApiModelProperty(value = "身份唯一标识，如：登录账号、邮箱地址、手机号码、QQ号码、微信号、微博号；")
    private String identifier;
    /**
    * 登录认证凭证(密码)
    */
    @ApiModelProperty(value = "登录认证凭证(密码)")
    private String credential;
    /**
    * 密码
    */
    private String password;
    /**
    * 登录类别，如：系统用户(0)、邮箱(1)、手机(2)，或者第三方的QQ、微信、微博；
    */
    @ApiModelProperty(value = "登录认证类别，如：系统用户(0)、邮箱(1)、手机(2)，或者第三方的QQ、微信、微博；")
    private Integer identityType;
    /**
    * 密码盐值
    */
    private String salt;
    /**
    * 状态(0 禁止登录 1允许登录) 默认 1
    */
    private Integer status;

    /**
     * 创建人
     */
    @TableField(value = "creator", fill = FieldFill.INSERT)
    protected Long creator;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    protected Date createTime;

    /**
     * 修改人
     */
    @TableField(value = "modifier", fill = FieldFill.INSERT_UPDATE)
    protected Long modifier;

    /**
     * 修改时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;

}