package com.stone.swagger.annotation;

import com.stone.swagger.config.SwaggerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * ZhcxSwagger注解
 * @title 
 * @date 2020年2月18日
 * @version 1.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ SwaggerAutoConfiguration.class })
public @interface EnableZhcxSwagger2 {
	
}
