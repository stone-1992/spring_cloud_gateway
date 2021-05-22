package com.stone.auth.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.stone.auth.model.domain.Person;
import com.stone.auth.model.vo.PersonImport;
import com.stone.auth.model.vo.UserAuthQuery;
import com.stone.auth.service.PersonService;
import com.stone.common.poi.Assert;
import com.stone.common.poi.ExportUtil;
import com.stone.common.poi.ImportUtil;
import com.stone.core.util.PageBean;
import com.stone.core.util.R;
import com.stone.core.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import model.exption.BusinessException;
import model.group.AddGroup;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 员工信息Controller
 * @author stone
 */
@RestController
@RequestMapping("/person")
@Api(tags = "员工信息接口")
public class PersonController {

    @Resource
    private PersonService personService;

    @PostMapping("addPerson")
    @ApiOperation("新增用户信息")
    public R<Boolean> addPerson(@RequestBody Person person) {
        // 校验参数
        ValidatorUtils.validateEntity(person, AddGroup.class);
        return R.ok(personService.addPerson(person));
    }

    @PutMapping("updatePerson")
    @ApiOperation("更新用户信息")
    public R<Boolean> updatePerson(@RequestBody Person person) {
        return R.ok(personService.updatePerson(person));
    }

    @DeleteMapping("deletePerson")
    @ApiOperation("根据ID删除用户信息")
    public R<Boolean> deletePerson(@RequestParam(value = "id") Long id) {
        return R.ok(personService.deleteByPersonId(id));
    }

    @GetMapping("selectPersonByPage")
    @ApiOperation("分页查询用户信息")
    public R<List<Person>> selectPersonByPage(@RequestParam("keyword") String keyword, @RequestParam("pageNo") Long pageNo, @RequestParam("pageSize") Long pageSize) {
        Page<Person> personPage = personService.selectPersonByPage(keyword, pageNo, pageSize);
        PageBean pageBean = PageBean.of(personPage.getCurrent(), personPage.getSize(), personPage.getTotal());
        return R.ok(personPage.getRecords(), pageBean);
    }

    @DeleteMapping("deletePersonBath")
    @ApiOperation("批量删除员工信息")
    public R<Boolean> deletePersonBath(@RequestBody UserAuthQuery userAuthQuery) {
        List<Long> ids = userAuthQuery.getIds();
        return R.ok(personService.deletePersonBatch(ids));
    }

    @ApiOperation(value = "下载导入员工模板")
    @GetMapping("/template")
    @SneakyThrows
    public void templatePerson(HttpServletResponse response) {
        String filePath = "template" + File.separator + "person.xlsx";
        ExportUtil.excelTemplateDown(response, "员工导入模板.xlsx", filePath);
    }

    @PostMapping("/import")
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
        List<Person> persons = Optional.ofNullable(personService.listPersons(new Person())).orElse(Lists.newArrayList());
        Map<String, Person> collect = persons.stream().collect(Collectors.toMap(Person::getMobile, obj -> obj, (k1, k2) -> k1));
        excelList.forEach(r->{
            String mobile = r.getMobile();
            Person person = collect.get(mobile);
            if(person != null){
                throw new BusinessException("员工已存在，请重新填写");
            }
        });

        // 5、新增
        List<Person> personList = Convert.convert(new TypeReference<List<Person>>() {}, excelList);
        boolean addBatch = personService.addBatch(personList);
        return R.ok(addBatch);
    }
}
