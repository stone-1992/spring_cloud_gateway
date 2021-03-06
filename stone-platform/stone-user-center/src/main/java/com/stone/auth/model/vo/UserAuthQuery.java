package com.stone.auth.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户查询类
 * @author stone
 */
@ApiModel
@Data
public class UserAuthQuery {

    @ApiModelProperty("删除ID集合")
    private List<Long> ids;
}
