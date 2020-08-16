package com.stone.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.common.datasource.mp.ServiceExpanderImpl;
import com.stone.mapper.PersonMapper;
import com.stone.service.PersonService;
import com.stone.vo.Person;

import javax.management.Query;
import java.util.List;
import java.util.Objects;

@Service(register = true)
public class PersonServiceImpl extends ServiceExpanderImpl<PersonMapper, Person> implements PersonService {


    @Override
    public boolean addPerson(Person person) {
        return super.save(person);
    }

    @Override
    public boolean deleteByPersonId(Long personId) {
        return super.removeById(personId);
    }

    @Override
    public boolean updatePerson(Person person) {
        UpdateWrapper<Person> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", person.getId());
        return super.update(person, updateWrapper);
    }

    @Override
    public Page<Person> selectPersonByPage(String keyword, Long pageNo, Long pageSize) {
        Page page = new Page(pageNo, pageSize);
        QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("person_name", keyword);
        return super.page(page, queryWrapper);
    }

    @Override
    public boolean deletePersonBatch(List<Long> personIds) {
        if(CollUtil.isEmpty(personIds)){
            return false;
        }
        return super.removeByIds(personIds);
    }

    @Override
    public boolean addBatch(List<Person> personList) {
        if(CollUtil.isEmpty(personList)){
            return false;
        }
        return super.insertBatchSomeColumn(personList) > 0 ? true : false;
    }

    @Override
    public List<Person> listPersons(Person person) {
        if(Objects.isNull(person)){
            return null;
        }
        return super.list(Wrappers.query(person));
    }
}
