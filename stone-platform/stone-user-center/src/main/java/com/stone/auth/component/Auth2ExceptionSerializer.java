package com.stone.auth.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;

/**
 * @classname Auth2ExceptionSerializer
 * @description OAuth2 异常格式化
 * @author stone
 */
public class Auth2ExceptionSerializer extends StdSerializer<WebAppResponseExceptionTranslator.Auth2Exception> {

	public Auth2ExceptionSerializer() {
		super(WebAppResponseExceptionTranslator.Auth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(WebAppResponseExceptionTranslator.Auth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeStringField("error",value.getErrorCode());
		gen.writeStringField("error_description",value.getLocalizedMessage());
		gen.writeObjectField("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
		gen.writeEndObject();
	}

}
