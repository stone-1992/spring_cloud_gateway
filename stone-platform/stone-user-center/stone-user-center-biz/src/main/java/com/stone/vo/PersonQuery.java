package com.stone.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 员工查询类
 * @author stone
 */
@ApiModel
@Data
public class PersonQuery {

    @ApiModelProperty("删除ID集合")
    private List<Long> ids;
}
