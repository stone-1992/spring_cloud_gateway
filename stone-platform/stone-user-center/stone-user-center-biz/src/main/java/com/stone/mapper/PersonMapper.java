package com.stone.mapper;

import com.stone.common.datasource.mp.BaseMapperExpander;
import com.stone.vo.Person;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工 Mapper
 * @author stone
 */
@Mapper
public interface PersonMapper extends BaseMapperExpander<Person> {

}
