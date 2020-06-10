package com.stone.core.validator.annotations;

import com.stone.core.validator.impl.BigDecimalValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @classname BigDecimalVail
 * @description 校验Bigdecimal 格式是否正确  允许值为null
 * @date 2020/5/19 17:22
 * @author 石宏利
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = BigDecimalValidator.class)
public @interface BigDecimalCheck {

    /*最小值*/
    double minValue() default Double.MIN_VALUE;

    /*最大值*/
    double maxValue() default Double.MAX_VALUE;

    /*整数位数*/
    int integer() default Integer.MAX_VALUE;

    /*小数位数*/
    int fraction() default Integer.MAX_VALUE;

    String message() default "数据填写格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
