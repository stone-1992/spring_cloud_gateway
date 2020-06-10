package com.stone.core.validator.impl;

import cn.hutool.core.util.StrUtil;
import com.stone.core.validator.annotations.BigDecimalCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * @classname BigDecimalVail
 * @description 校验Bigdecimal 格式是否正确  允许值为null
 * @date 2020/5/19 17:22
 * @author 石宏利
 */
public class BigDecimalValidator implements ConstraintValidator<BigDecimalCheck, BigDecimal> {


    private double minValue;

    /*最大值*/
    private double maxValue;

    /*整数位数*/
    private int integer;

    /*小数位数*/
    private int fraction;

    @Override
    public void initialize(BigDecimalCheck constraintAnnotation) {
        this.minValue = constraintAnnotation.minValue();
        this.maxValue = constraintAnnotation.maxValue();
        this.integer = constraintAnnotation.integer();
        this.fraction = constraintAnnotation.fraction();
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        // 校验最小值
        if (value.compareTo(new BigDecimal(minValue)) < 0) {
            return false;
        }
        // 校验最大值
        if (value.compareTo(new BigDecimal(maxValue)) > 0) {
            return false;
        }
        String val = String.valueOf(value);
        if(StrUtil.isBlank(val)){
            return true;
        }
        String[] split = val.split("\\.");
        // 校验整数位数
        if(split.length > 0){
            if(split[0].replace("-","").length() > integer){
                return false;
            }
        }
        // 校验小数位数
        if(split.length > 1){
            if(split[1].length() > fraction){
                return false;
            }
        }
        return true;
    }
}
