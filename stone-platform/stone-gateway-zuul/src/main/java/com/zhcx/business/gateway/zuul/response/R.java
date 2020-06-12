package com.zhcx.business.gateway.zuul.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 响应信息主体
 * 
 * @title
 * @author 龚进
 * @date 2020年4月2日
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
@Data
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_MSG = "操作成功";

	private static final int SUCCESS = 200;

	private static final int FAIL = 500;

	private int code;

	private Boolean result;

	@Builder.Default
	private String resultDesc = DEFAULT_MSG;

	private T data;

	public static <T> R<T> ok() {
		return restResult(null, SUCCESS, true, DEFAULT_MSG);
	}

	public static <T> R<T> ok(T data) {
		return restResult(data, SUCCESS, true, DEFAULT_MSG);
	}

	public static <T> R<T> ok(T data, String msg) {
		return restResult(data, SUCCESS, true, msg);
	}

	public static <T> R<T> ok(int code, String msg) {
		return restResult(null, SUCCESS, true, msg);
	}

	public static <T> R<T> ok(T data, int code, String msg) {
		return restResult(data, SUCCESS, true, msg);
	}

	public static <T> R<T> failed() {
		return restResult(null, FAIL, false, null);
	}

	public static <T> R<T> failed(String msg) {
		return restResult(null, FAIL, false, msg);
	}

	public static <T> R<T> failed(T data) {
		return restResult(data, FAIL, false, null);
	}

	public static <T> R<T> failed(T data, String msg) {
		return restResult(data, FAIL, false, msg);
	}

	public static <T> R<T> failed(T data, int code, String msg) {
		return restResult(data, code, false, msg);
	}

	public static <T> R<T> failed(int code, String msg) {
		return restResult(null, code, false, msg);
	}

	public static <T> R<T> fail401() {
		return restResult(null, HttpStatus.UNAUTHORIZED.value(), false, "请重新登录");
	}

	public static <T> R<T> fail403() {
		return restResult(null, HttpStatus.FORBIDDEN.value(), false, "访问拒绝!原因:无权限");
	}

	public static <T> R<T> fail404() {
		return restResult(null, HttpStatus.NOT_FOUND.value(), false, "服务无响应");
	}

	public static <T> R<T> fail500() {
		return restResult(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "网关系统异常");
	}

	public static <T> R<T> fail503(String errorMsg) {
		return restResult(null, HttpStatus.SERVICE_UNAVAILABLE.value(), false, String.format("服务不可用:%s", errorMsg));
	}

	private static <T> R<T> restResult(T data, int code, Boolean result, String msg) {
		R<T> apiResult = new R<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setResult(result);
		apiResult.setResultDesc(msg);
		return apiResult;
	}
}
