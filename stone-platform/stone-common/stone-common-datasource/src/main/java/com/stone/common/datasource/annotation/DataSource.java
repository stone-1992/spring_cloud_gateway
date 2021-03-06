package com.stone.common.datasource.annotation;

import java.lang.annotation.*;

/**
 * @classname DataSource
 * @description 多数据源注解
 * @date 2019/11/5 13:25
 * @author stone
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    String value() default "";
}
