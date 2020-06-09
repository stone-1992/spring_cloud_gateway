package com.stone.entity.vo;

import core.validator.annotations.BigDecimalCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import model.group.AddGroup;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author 石宏利
 * @description 站点前端传递对象
 * @date 2020/5/18 10:29
 */
@ApiModel("站点对象")
@Data
public class SiteVO {

    @ApiModelProperty("场站Id")
    private Long id;

    @ApiModelProperty("站点编号")
    private String siteCode;

    @ApiModelProperty("站点名称")
    @NotBlank(message = "站点名称不能为空", groups = {AddGroup.class})
    @Length(max = 30, message = "站点名称不能超过30个字符", groups = {AddGroup.class})
    private String siteName;

    @ApiModelProperty("金钱")
    @NotNull(message = "金钱不能为空", groups = {AddGroup.class})
    @BigDecimalCheck(minValue = -180.00f, maxValue = 180.00f, fraction = 7, message = "金钱范围-180~180,最多7位小数", groups = {AddGroup.class})
    private BigDecimal money;

}
