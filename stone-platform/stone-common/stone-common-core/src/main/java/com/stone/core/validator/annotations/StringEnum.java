package com.stone.core.validator.annotations;


import com.stone.core.validator.impl.StringEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @classname StrEnum
 * @description String类型枚举值校验 允许值为null
 * @date 2020/4/21 10:24
 * @author stone
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = StringEnumValidator.class)
public @interface StringEnum {
    String[] values() default {""};

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
