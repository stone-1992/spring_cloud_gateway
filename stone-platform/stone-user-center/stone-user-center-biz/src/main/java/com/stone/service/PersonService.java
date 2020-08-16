package com.stone.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.common.datasource.mp.IServiceExpander;
import com.stone.vo.Person;

import java.util.List;

public interface PersonService extends IServiceExpander<Person> {

    /**
     * 新增员工信息
     * @param person
     * @return
     */
    boolean addPerson(Person person);

    /**
     * 根据ID删除员工信息
     * @param personId
     * @return
     */
    boolean deleteByPersonId(Long personId);

    /**
     * 更新员工信息
     * @param person
     * @return
     */
    boolean updatePerson(Person person);

    /**
     * 分页查询员工信息
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Person> selectPersonByPage(String keyword, Long pageNo, Long pageSize);

    /**
     * 根据员工ID集合，批量删除员工信息
     * @param personIds
     * @return
     */
    boolean deletePersonBatch(List<Long> personIds);

    /**
     * 批量新增员工信息
     * @param personList
     * @return
     */
    boolean addBatch(List<Person> personList);

    /**
     * 查询所有的员工信息
     * @param person
     * @return
     */
    List<Person> listPersons(Person person);
}
