package core.validator.impl;

import core.validator.annotations.DateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.format.DateTimeFormatter;

/**
 * @classname DateTimeValidator
 * @description 日期格式校验器 yyyy-MM-dd HH:mm:ss
 * @date 2020/4/20 18:00
 * @author xhe
 */
public class DateTimeValidator implements ConstraintValidator<DateTime, String> {

    private String patten;

    @Override
    public void initialize(DateTime constraintAnnotation) {
        this.patten = constraintAnnotation.patten();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(null == value){
            return true;
        }
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patten);
            formatter.parse(value);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
