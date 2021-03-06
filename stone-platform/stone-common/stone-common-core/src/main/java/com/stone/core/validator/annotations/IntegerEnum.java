package com.stone.core.validator.annotations;


import com.stone.core.validator.impl.IntegerEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @classname IntegerEnum
 * @description int 类型枚举值校验 允许值为null
 * @date 2020/4/21 12:05
 * @author stone
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = IntegerEnumValidator.class)
public @interface IntegerEnum {

    int[] values() default {};

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
