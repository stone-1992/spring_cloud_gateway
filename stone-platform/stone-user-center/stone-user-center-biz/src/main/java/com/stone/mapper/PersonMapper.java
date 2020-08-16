package com.stone.mapper;

import com.stone.common.datasource.mp.BaseMapperExpander;
import com.stone.vo.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper extends BaseMapperExpander<Person> {

}
