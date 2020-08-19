package com.stone.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 菜单管理查询封装类
 * @author stone
 */
@ApiModel
@Data
public class MenuQuery {

    @ApiModelProperty("删除ID集合")
    private List<Long> ids;
}
