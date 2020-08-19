package model.exption;

import lombok.EqualsAndHashCode;

/**
 * 验证码异常
 * @author stone
 */
@EqualsAndHashCode(callSuper = false)
public class CaptchaException extends RuntimeException {

	private static final long serialVersionUID = -6802277992804731690L;

	public CaptchaException(String msg) {
		super(msg);
	}
}
