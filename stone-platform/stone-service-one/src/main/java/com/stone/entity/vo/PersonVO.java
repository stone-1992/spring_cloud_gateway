package com.stone.entity.vo;

import com.stone.core.validator.annotations.IntegerEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import model.group.AddGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 员工导入类
 *
 * @version 1.0
 * @title
 * @date 2020年5月13日
 */
@ApiModel("员工类")
@Data
public class PersonVO {

    @Size(max = 30, message = "员工姓名不能超过30个字符", groups = {AddGroup.class})
    @NotBlank(message = "员工姓名不能为空", groups = {AddGroup.class})
    private String personName;

    @Size(max = 30, message = "员工手机号不能超过30个字符", groups = {AddGroup.class})
    @NotBlank(message = "员工手机号不能为空", groups = {AddGroup.class})
    @Pattern(regexp = "^[0-9]*$",message = "手机号码只能是数字",groups = {AddGroup.class})
    private String personMobile;

    @IntegerEnum(values = {0, 1, 2}, message = "性别不合法", groups = {AddGroup.class})
    private Integer gender;
}
