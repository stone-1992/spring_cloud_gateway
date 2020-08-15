package com.stone.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel
@Data
public class UserAuthQuery {

    @ApiModelProperty("删除ID集合")
    private List<Long> ids;
}
