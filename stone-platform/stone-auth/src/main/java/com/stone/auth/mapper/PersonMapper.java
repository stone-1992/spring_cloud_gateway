package com.stone.auth.mapper;

import com.stone.auth.model.domain.Person;
import com.stone.common.datasource.mp.BaseMapperExpander;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工 Mapper
 * @author stone
 */
@Mapper
public interface PersonMapper extends BaseMapperExpander<Person> {

}
