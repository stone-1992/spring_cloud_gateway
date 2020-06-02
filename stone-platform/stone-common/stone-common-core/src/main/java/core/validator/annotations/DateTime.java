package core.validator.annotations;


import core.validator.impl.DateTimeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @classname DateTime
 * @description 验证是否是日期时间类型 yyyy-MM-dd HH:mm:ss  允许值为null
 * @date 2020/4/20 17:58
 * @author xhe
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = DateTimeValidator.class)
public @interface DateTime {

    String patten() default "yyyy-MM-dd HH:mm:ss";

    String message() default "日期格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
