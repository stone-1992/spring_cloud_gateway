package com.zhcx.business.common.datasource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @classname DynamicDataSource
 * @description 多数据源
 * @date 2019/11/5 13:25
 * @author xhe
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicContextHolder.peek();
    }

}
