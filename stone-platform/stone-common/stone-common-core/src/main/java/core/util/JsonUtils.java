package core.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;

/**
 * @author zfq
 * @version 1.0
 * @description
 * @date 2020年04月14日 14:20
 */
public class JsonUtils {
	public static ObjectMapper objectMapper = new ObjectMapper();

	static {
		// 转换为格式化的json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

		// 如果json中有新增的字段并且是实体类类中不存在的，不报错
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * 把json转成javaBean
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T json2Bean(String json, Class<T> clazz) throws Exception {
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
	public static <T> List<T> json2List(String json, Class<T> clazz) throws Exception {
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
	public static <T> String bean2Json(T bean) throws Exception {
		String json = objectMapper.writeValueAsString(bean);
		return json;
	}
}
