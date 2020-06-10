package com.stone.core.validator.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.stone.core.validator.annotations.StringCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 石宏利
 * @classname StringValidator
 * @description 校验String 格式是否正确  允许值为null 和空字符串
 * @date 2020/5/28 17:22
 */
public class StringValidator implements ConstraintValidator<StringCheck, String> {

    /**
     * 最小长度
     */
    int min;

    /**
     * 最大长度
     */
    int max;

    /**
     * 正则表达式
     */
    String pattern;

    /**
     * 是否纯数字
     */
    boolean isNumber;

    @Override
    public void initialize(StringCheck constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.pattern = constraintAnnotation.pattern();
        this.isNumber = constraintAnnotation.isNumber();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 如果字符串为null
        if (StrUtil.isBlank(value)) {
            return true;
        }
        // 校验字符串长度最小值
        if (value.length() < min) {
            return false;
        }
        // 校验字符串长度最大值
        if (value.length() > max) {
            return false;
        }
        try {
            // 正则表达式
            if (StrUtil.isNotBlank(pattern) && !value.matches(pattern)) {
                return false;
            }
            // 是否纯数字
            if (isNumber) {
                if (!NumberUtil.isNumber(value)) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
