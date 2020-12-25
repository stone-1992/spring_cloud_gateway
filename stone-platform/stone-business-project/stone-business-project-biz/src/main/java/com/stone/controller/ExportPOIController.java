package com.stone.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import com.alibaba.excel.EasyExcel;
import com.google.common.collect.Maps;
import com.stone.common.poi.Assert;
import com.stone.common.poi.ExportUtil;
import com.stone.common.poi.ImportUtil;
import com.stone.entity.vo.PersonExport;
import com.stone.entity.vo.PersonImport;
import com.stone.entity.vo.PersonVO;
import com.stone.utils.CreateBeanUtils;
import com.stone.core.util.R;
import com.stone.core.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import model.group.AddGroup;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 导出列
 *
 * @author stone
 */
@Api(tags = "导入导出API接口")
@RequestMapping("/export")
@RestController
public class ExportPOIController {

    @ApiOperation(value = "下载导入员工模板")
    @GetMapping("/template/person")
    @SneakyThrows
    public void templatePerson(HttpServletResponse response) {
        String fileName = "导入员工模板.xlsx";
        String filePath = "template" + File.separator + "person_template.xlsx";
        ExportUtil.excelTemplateDown(response, fileName, filePath);
    }

    @PostMapping("/import/person")
    @ApiOperation(value = "导入员工信息", notes = "")
    @SneakyThrows
    public R<Boolean> personDataImport(@RequestParam("file") MultipartFile file) {
        // 1、校验文件格式、大小等
        ImportUtil.importFileValidate(file);

        // 2、读取Excel中的数据
        List<PersonImport> excelList = EasyExcel.read(file.getInputStream()).head(PersonImport.class)
                // 从第二行开始读（忽略excel模板说明）
                .headRowNumber(2).sheet().autoTrim(true).doReadSync();
        Assert.isTrue(CollUtil.isNotEmpty(excelList), "导入数据为空");
        Assert.isTrue(excelList.size() <= 8000, "导入条数不能超过8000条");

        // 3、校验Excel中数据填写是否符合规则
        for (int i = 0; i < excelList.size(); i++) {
            PersonImport personImport = excelList.get(i);
            ValidatorUtils.validateEntity(personImport, String.format("第%s行", (i + 2 + 1)), AddGroup.class);
        }
        System.err.println(" excelList :  " + excelList);
        // 4、拿到excelList中的数据做业务处理，比如判重之后新增数据，判重统一用全查，代码里判重，不要遍历查数据库查重
        // .....

        return R.ok(true);
    }

    @ApiOperation(value = "导出员工列表", notes = "")
    @GetMapping("/export")
    @SneakyThrows
    public void export(@ModelAttribute PersonVO personVO, HttpServletResponse response) {
        // 1、文件名
        String fileName = "员工数据列表_" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + "";

        // 2、查询数据
        List<PersonVO> personVOList = CreateBeanUtils.getPersonVOList();
        List<PersonExport> result = Convert.convert(new TypeReference<List<PersonExport>>() {
        }, personVOList);

        Map<String, String> genderMap = Maps.newConcurrentMap();
        genderMap.put("0", "未知");
        genderMap.put("1", "男");
        genderMap.put("2", "女");
        // 3、对字典值进行处理
        for (PersonExport personExport : result) {
            if (genderMap.containsKey(personExport.getGender())) {
                personExport.setGender(genderMap.get(personExport.getGender()));
            }
        }
        // 4、导出
        ExportUtil.export(result, response, fileName, PersonExport.class);
    }

}
