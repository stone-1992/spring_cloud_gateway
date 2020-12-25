package model.exption;

import lombok.EqualsAndHashCode;

/**
 * 参数异常
 * @title 
 * @date 2019年11月12日
 * @version 1.0
 * @author stone
 */
@EqualsAndHashCode(callSuper = false)
public class ParamException extends RuntimeException {

	public ParamException(String msg){
		super(msg);
	}
}
