package com.stone.gateway.zuul.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.TimeZone;


/**
 * Json和javaBean转换工具类
 * 
 * @title
 * @date 2017年11月24日
 * @version 1.0
 */
public class JacksonUtils {

	private static ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
	}

	/**
	 * 把json转成javaBean
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T json2Bean(String json, Class<T> clazz) throws JsonProcessingException, JsonMappingException {
		T bean = objectMapper.readValue(json, clazz);
		return bean;
	}

	/**
	 * 把json转为list
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> json2List(String json, Class<T> clazz)
			throws JsonProcessingException, JsonMappingException {
		List<T> listBean = objectMapper.readValue(json, new TypeReference<List<T>>() {
		});
		return listBean;
	}

	/**
	 * 把javaBean转成json
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static <T> String bean2Json(T bean) throws JsonProcessingException {
		String json = objectMapper.writeValueAsString(bean);
		return json;
	}
}
