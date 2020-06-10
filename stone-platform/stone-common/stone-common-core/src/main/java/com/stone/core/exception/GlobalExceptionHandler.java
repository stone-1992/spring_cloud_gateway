package com.stone.core.exception;


import com.stone.core.util.R;
import lombok.extern.slf4j.Slf4j;
import model.exption.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @classname GlobalExceptionHandler
 * @description 全局异常处理器
 * @date 2018/5/11
 */
@Slf4j
@RestControllerAdvice
@ConditionalOnProperty(prefix = "zhcx.business.global-exception", name = "enabled", havingValue = "true", matchIfMissing = true)
public class GlobalExceptionHandler {
	/**
	 * 参数校验异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ParamException.class)
	public R handleParamException(ParamException e) {
		log.warn(e.getMessage());
		return R.failed(e.getMessage());
	}

	/**
	 * 参数校验异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(CaptchaException.class)
	public R handleCaptchaException(CaptchaException e) {
		log.warn(e.getMessage());
		return R.failed(e.getMessage());
	}

	/**
	 * 业务异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	public R handleBusinessException(BusinessException e) {
		log.warn(e.getMessage());
		return R.failed(e.getMessage());
	}

	/**
	 * 处理流相关自定义异常
	 * 
	 * @param e
	 * @throws Exception
	 */
	@ExceptionHandler(ResponseException.class)
	public R handleResponseException(ResponseException e) {
		log.info("导出失败：{}", e.getMessage());
		return R.failed(e.getMessage());
	}

	/**
	 * 处理401异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public R handleUnauthorizedException(UnauthorizedException e) {
		return R.failed(e.getMessage());
	}

	/**
	 * 处理上传文件过大异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public R handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
		return R.failed("上传文件大小超过限制");
	}

	/**
	 * 未知异常
	 * 
	 * @param e
	 */
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public R<Object> handleException(Exception e) {
		log.error("系统异常");
		log.error(e.getMessage(), e);
		return R.failed("系统异常");
	}
}
