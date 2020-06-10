package com.stone.core.util;

import com.stone.core.constant.CommonConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
@ApiModel(value = "响应信息主体")
@Data
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_MSG = "操作成功";

	@ApiModelProperty(value = "返回标记：失败标记=0，成功标记=1")
	private int code;

	@ApiModelProperty(value = "返回信息")
	private Boolean result;

	@ApiModelProperty(value = "返回信息")
	@Builder.Default
	private String resultDesc = DEFAULT_MSG;

	@ApiModelProperty(value = "数据")
	private T data;

	@ApiModelProperty(value = "分页对象")
	private PageBean pageBean;

	public static <T> R<T> ok() {
		return restResult(null,new PageBean(), CommonConstants.SUCCESS,true, DEFAULT_MSG);
	}

	public static <T> R<T> ok(T data) {
		return restResult(data,new PageBean(),CommonConstants.SUCCESS,true, DEFAULT_MSG);
	}

	public static <T> R<T> ok(T data,PageBean pageBean) {
		return restResult(data,pageBean,CommonConstants.SUCCESS,true, DEFAULT_MSG);
	}

	public static <T> R<T> ok(T data, String msg) {
		return restResult(data,null,CommonConstants.SUCCESS,true, msg);
	}

	public static <T> R<T> ok(T data,PageBean pageBean,String msg) {
		return restResult(data,pageBean,CommonConstants.SUCCESS,true, msg);
	}

	public static <T> R<T> ok(int code, String msg) {
		return restResult(null,new PageBean(), CommonConstants.SUCCESS,true, msg);
	}

	public static <T> R<T> ok(T data,int code,String msg) {
		return restResult(data,new PageBean(),CommonConstants.SUCCESS,true, msg);
	}

	public static <T> R<T> ok(T data,PageBean pageBean,int code,String msg) {
		return restResult(data,pageBean,code,true, msg);
	}

	public static <T> R<T> failed() {
		return restResult(null,null,CommonConstants.FAIL,false, null);
	}

	public static <T> R<T> failed(String msg) {
		return restResult(null,null,CommonConstants.FAIL,false, msg);
	}

	public static <T> R<T> failed(T data) {
		return restResult(data,null,CommonConstants.FAIL, false,null);
	}

	public static <T> R<T> failed(T data, String msg) {
		return restResult(data,null, CommonConstants.FAIL,false, msg);
	}

	private static <T> R<T> restResult(T data,PageBean pageBean,int code,Boolean result,String msg) {
		R<T> apiResult = new R<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setPageBean(pageBean);
		apiResult.setResult(result);
		apiResult.setResultDesc(msg);
		return apiResult;
	}
}
