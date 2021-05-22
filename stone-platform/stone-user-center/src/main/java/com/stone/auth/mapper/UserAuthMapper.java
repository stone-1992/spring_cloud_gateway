package com.stone.auth.mapper;

import com.stone.auth.model.domain.UserAuth;
import com.stone.common.datasource.mp.BaseMapperExpander;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户 Mapper
 * @author stone
 */
@Mapper
public interface UserAuthMapper extends BaseMapperExpander<UserAuth> {

}
