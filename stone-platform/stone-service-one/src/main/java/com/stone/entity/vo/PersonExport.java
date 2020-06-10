package com.stone.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 员工导入类
 *
 * @version 1.0
 * @title
 * @date 2020年5月13日
 */
@ApiModel("员工导出类")
@Data
public class PersonExport {

    @ColumnWidth(20)
    @ExcelProperty("姓名")
    private String personName;

    @ColumnWidth(20)
    @ExcelProperty("手机号")
    private String personMobile;

    @ColumnWidth(20)
    @ExcelProperty(value = "性别")
    private String gender;
}
