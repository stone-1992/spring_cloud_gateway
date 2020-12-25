package com.stone.common.dubbo.annotation;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.core.annotation.AliasFor;
import java.lang.annotation.*;

/**
 * dubbo服务注解
 * @title 
 * @date 2020年3月3日
 * @version 1.0
 * @author stone
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Service
public @interface DubboService {

	@AliasFor(annotation = Service.class, attribute = "version")
	String dubboVersion() default "";

	@AliasFor(annotation = Service.class, attribute = "interfaceClass")
	Class<?> dubboInterfaceClass() default void.class;

	@AliasFor(annotation = Service.class, attribute = "interfaceName")
	String dubboInterfaceName() default "";
}
