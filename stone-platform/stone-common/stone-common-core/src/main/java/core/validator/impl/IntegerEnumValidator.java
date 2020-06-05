package core.validator.impl;

import core.validator.annotations.IntegerEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @classname IntegerEnumValidator
 * @description
 * @date 2020/4/21 13:31
 */
public class IntegerEnumValidator implements ConstraintValidator<IntegerEnum,Integer> {

    private int[] values;

    @Override
    public void initialize(IntegerEnum constraintAnnotation) {
        int[] values = constraintAnnotation.values();
        if(null == values){
            values = new int[0];
        }
        this.values = values;
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(null == value){
            return true;
        }
        for(int i:values){
            if(value == i){
                return true;
            }
        }
        return false;
    }
}
