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
 * 用户中心
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_user_auth")
public class UserAuth implements Serializable {

    private static final long serialVersionUID = 2543917194612051388L;

    /**
     * 唯一id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @NotNull(message = "用户ID不能为空", groups = {AddGroup.class})
    private Long userId;

    /**
     * 账号
     */
    @TableField(value = "account_no")
    @NotBlank(message = "账号不能为空", groups = { AddGroup.class})
    private String accountNo;

    /**
     * 密码
     */
    @TableField(value = "account_pwd")
    @NotBlank(message = "密码不能为空", groups = { AddGroup.class})
    private String accountPwd;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;
}
