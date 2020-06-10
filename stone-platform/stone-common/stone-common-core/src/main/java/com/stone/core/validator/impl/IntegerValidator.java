package com.stone.core.validator.impl;

import com.stone.core.validator.annotations.IntegerCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 石宏利
 * @classname IntegerValidator
 * @description 校验Integer 格式是否正确  允许值为null
 * @date 2020/5/19 17:22
 */
public class IntegerValidator implements ConstraintValidator<IntegerCheck, Integer> {


    /**
     * 最小值
     */
    private int minValue;

    /**
     * 最大值
     */
    private int maxValue;


    @Override
    public void initialize(IntegerCheck constraintAnnotation) {
        this.minValue = constraintAnnotation.minValue();
        this.maxValue = constraintAnnotation.maxValue();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        // 校验最小值
        if (value < minValue) {
            return false;
        }
        // 校验最大值
        if (value > maxValue) {
            return false;
        }
        return true;
    }
}
