package com.stone.auth.model.vo;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import model.group.AddGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 员工导入类
 *
 * @version 1.0
 * @title
 * @date 2020年5月13日
 * @author stone
 */
@ApiModel("员工导入类")
@Data
public class PersonImport {

    @Size(max = 30, message = "员工姓名不能超过30个字符", groups = {AddGroup.class})
    @NotBlank(message = "员工姓名不能为空", groups = {AddGroup.class})
    @ExcelProperty("姓名")
    private String personName;

    @Size(max = 30, message = "员工手机号不能超过30个字符", groups = {AddGroup.class})
    @NotBlank(message = "员工手机号不能为空", groups = {AddGroup.class})
    @Pattern(regexp = "^[0-9]*$",message = "手机号码只能是数字",groups = {AddGroup.class})
    @ExcelProperty("手机号码")
    private String mobile;

    @NotBlank(message = "员工卡号不能为空", groups = { AddGroup.class})
    @ExcelProperty(value = "员工卡号")
    private String cardNumber;

    @NotNull(message = "入职日期不能为空", groups = { AddGroup.class})
    @ExcelProperty(value = "入职日期")
    private String entryDateExt;

    /**
     * 入职日期
     */
    private Date entryDate;

    @NotNull(message = "年龄不能为空", groups = { AddGroup.class})
    @ExcelProperty(value = "年龄")
    private Integer age;

    /**
     * 性别：1 男， 0 女
     */
    private Integer gender;

    @ExcelProperty(value = "性别")
    private String genderExt;

    public Integer getGender(){
        if("男".equals(genderExt)){
            return 1;
        }else {
            return 0;
        }
    }

    public Date getEntryDate(){
        return DateUtil.parseDate(entryDateExt);
    }
}
