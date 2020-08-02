package com.stone.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long userId;

    /**
     * 账号
     */
    @TableField(value = "account_no")
    private String accountNo;

    /**
     * 密码
     */
    @TableField(value = "account_pwd")
    private String accountPwd;
}
