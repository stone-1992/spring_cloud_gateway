package model.exption;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @classname BusinessException
 * @description 业务异常
 * @date 2020/4/23 9:59
 * @author xhe
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {
	private int code;

    public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(int code, String msg) {
    	super(msg);
		this.code = code;
	}
}
