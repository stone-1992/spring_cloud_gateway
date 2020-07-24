package com.stone.auth.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @classname UserInfoDTO
 * @description 用户全部信息传输对象
 * @date 2020/3/12 10:04
 */
@Data
@ApiModel(value = "用户完整信息")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = 2378941172045135971L;

    @ApiModelProperty(value = "用户账户认证信息")
    private UserAuth userAuth;
}
