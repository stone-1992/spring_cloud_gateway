package swagger.annotation;

import org.springframework.context.annotation.Import;
import swagger.config.SwaggerAutoConfiguration;

import java.lang.annotation.*;

/**
 * ZhcxSwagger注解
 * @title 
 * @author 龚进
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
