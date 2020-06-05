package com.zhcx.business.common.datasource.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import model.dto.AuthUserInfoDTO;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author xhe
 * @classname MyBatisPlusConfig
 * @description MyBatisPlus配置
 * @date 2019/7/18
 */
@Slf4j
@Configuration
public class MyBatisPlusConfig {

    @Bean
    public ISqlInjector iSqlInjector() {
        return new SqlInjectorExpander();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 字段填充处理器
     *
     * @return
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        log.info("加载mybatis字段填充处理器成功...");
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                log.debug("自动插入创建时间");
                this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
                this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
                AuthUserInfoDTO userInfo = new AuthUserInfoDTO();
                userInfo.setUserId(100L);
                if (null != userInfo) {
                    log.debug("自动插入创建人 userId:{}", userInfo.getUserId());
                    this.strictInsertFill(metaObject, "creator", Long.class, userInfo.getUserId());
                    log.debug("自动插入修改人 userId:{}", userInfo.getUserId());
                    this.strictInsertFill(metaObject, "modifier", Long.class, userInfo.getUserId());
                }
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                log.debug("自动插入更新时间");
                this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
                AuthUserInfoDTO userInfo = new AuthUserInfoDTO();
                userInfo.setUserId(100L);
                if (null != userInfo) {
                    log.debug("自动插入更新人 userId:{}", userInfo.getUserId());
                    this.strictUpdateFill(metaObject, "modifier", Long.class, userInfo.getUserId());
                }
            }
        };
    }
}
