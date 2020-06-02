package com.zhcx.business.common.datasource.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.experimental.UtilityClass;
import java.util.Date;

/**
 * @author xhe
 * @classname WrapperUtils
 * @description 条件构造工具类
 * @date 2020/5/20 14:24
 */
@UtilityClass
public class WrapperUtils {
    /**
     * 添加有效时间条件
     * @param queryWrapper 查询包装器
     */
    public static <T> QueryWrapper<T> addEffTimeCondition(QueryWrapper<T> queryWrapper){
        return queryWrapper.le("eff_start_time",new Date())
                .and(wrapper ->wrapper.isNull("eff_end_time")
                        .or().ge("eff_end_time",new Date())
                );
    }

    /**
     * 添加有效时间条件
     * @param queryWrapper 查询包装器
     */
    public static <T> QueryWrapper<T> addEffTimeCondition(QueryWrapper<T> queryWrapper,String effStartTimeStr,String effEndTimeStr){
        return queryWrapper.le(effStartTimeStr,new Date())
                .and(wrapper ->wrapper.isNull(effEndTimeStr)
                        .or().ge(effEndTimeStr,new Date())
                );
    }

    /**
     * 添加有效时间条件
     * @param updateWrapper 更新包装器
     */
    public static <T> UpdateWrapper<T> addEffTimeCondition(UpdateWrapper<T> updateWrapper){
        return updateWrapper.le("eff_start_time",new Date())
                .and(wrapper ->wrapper.isNull("eff_end_time")
                        .or().ge("eff_end_time",new Date())
                );
    }
}
