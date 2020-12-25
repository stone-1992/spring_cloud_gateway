package com.stone.core.validator.impl;

import com.stone.core.validator.annotations.StringEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * @Classname StringEnumValidator
 * @Description
 * @Date 2020/4/21 11:55
 * @author stone
 */
public class StringEnumValidator implements ConstraintValidator<StringEnum, String> {

    private String[] values;

    @Override
    public void initialize(StringEnum constraintAnnotation) {
        this.values = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(null == value){
            return true;
        }
        if(!Arrays.asList(values).contains(value)){
            return false;
        }
        return true;
    }
}
