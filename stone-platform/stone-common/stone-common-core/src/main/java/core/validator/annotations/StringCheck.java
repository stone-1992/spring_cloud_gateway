package core.validator.annotations;


import core.validator.impl.StringValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author 石宏利
 * @classname StringCheck
 * @description 校验String 格式是否正确  允许值为null 和 空字符串, 允许值为isBlank校验
 * @date 2020/5/28 17:22
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = StringValidator.class)
public @interface StringCheck {

    /**
     * 最小长度
     *
     * @return
     */
    int min() default 0;

    /**
     * 最大长度
     *
     * @return
     */
    int max() default Integer.MAX_VALUE;

    /**
     * 正则表达式
     *
     * @return
     */
    String pattern() default "";

    /**
     * 是否纯数字
     *
     * @return
     */
    boolean isNumber() default false;

    String message() default "字符串格式匹配不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
