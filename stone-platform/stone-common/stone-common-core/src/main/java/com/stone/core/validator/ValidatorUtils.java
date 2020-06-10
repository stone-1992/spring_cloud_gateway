package com.stone.core.validator;

import model.exption.ParamException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 校验工具类
 *
 * @title
 * @date 2019年11月14日
 * @version 1.0
 */
public class ValidatorUtils {

	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * 校验对象
	 *
	 * @param object
	 *            对象
	 * @param groups
	 *            分组
	 */
	public static void validateEntity(Object object, Class<?>... groups){
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
		if (null != constraintViolations && !constraintViolations.isEmpty()) {
			for (ConstraintViolation<Object> constraint : constraintViolations) {
				throw new ParamException(constraint.getMessage());
			}
		}
	}

	/**
	 * 校验多个参数
	 *
	 * @param object
	 *            属性对象
	 * @param fields
	 *            验证属性数组
	 * @param groups
	 *            分组
	 */
	public static void validateParam(Object object, String[] fields, Class<?>... groups){
		for (String field : fields) {
			Set<ConstraintViolation<Object>> constraintViolations = validator.validateProperty(object, field, groups);
			if (null != constraintViolations && !constraintViolations.isEmpty()) {
				for (ConstraintViolation<Object> constraint : constraintViolations) {
					throw new ParamException(constraint.getMessage());
				}
			}
		}
	}

	/**
	 * 校验多个参数
	 *
	 * @param object
	 *            属性对象
	 * @param fields
	 *            验证属性数组
	 * @param customMsg 自定义提示消息
	 * @param groups
	 *            分组
	 */
	public static void validateParam(Object object, String[] fields, String customMsg, Class<?>... groups){
		for (String field : fields) {
			Set<ConstraintViolation<Object>> constraintViolations = validator.validateProperty(object, field, groups);
			if (null != constraintViolations && !constraintViolations.isEmpty()) {
				for (ConstraintViolation<Object> constraint : constraintViolations) {
					throw new ParamException(constraint.getMessage() + customMsg);
				}
			}
		}
	}

	/**
	 * 校验对象
	 *
	 * @param object
	 *            对象
	 * @param customMsg 自定义提示消息
	 * @param groups
	 *            分组
	 */
	public static void validateEntity(Object object, String customMsg, Class<?>... groups){
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
		if (null != constraintViolations && !constraintViolations.isEmpty()) {
			for (ConstraintViolation<Object> constraint : constraintViolations) {
				throw new ParamException(constraint.getMessage() + customMsg);
			}
		}
	}
}
