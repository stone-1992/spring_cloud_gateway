package com.stone.core.validator.annotations;


import com.stone.core.validator.impl.IntegerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author 石宏利
 * @classname IntegerCheck
 * @description 校验Integer 格式是否正确  允许值为null
 * @date 2020/5/27 17:22
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = IntegerValidator.class)
public @interface IntegerCheck {

    /*最小值*/
    int minValue() default Integer.MIN_VALUE;

    /*最大值*/
    int maxValue() default Integer.MAX_VALUE;

    String message() default "数据填写格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
