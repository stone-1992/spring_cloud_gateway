package com.stone.gateway.zuul.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

/**
 * 响应工具类
 * 
 * @title
 * @date 2020年4月2日
 * @version 1.0
 */
public class ResponseUtils {

	/**
	 * 往响应流里面写数据
	 * @param response
	 * @param result
	 * @throws IOException
	 */
	public static void writeResponse(HttpServletResponse response, String result,HttpStatus httpStatus) throws IOException {
		response.setStatus(httpStatus.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.getOutputStream().write(result.getBytes());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}
