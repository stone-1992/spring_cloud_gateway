package com.stone.gateway.zuul.hystrix;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.stone.gateway.zuul.response.R;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.stone.gateway.zuul.util.JacksonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 服务断路处理
 * 
 * @title
 * @date 2020年4月10日
 * @version 1.0
 */
@Slf4j
@Component
public class ApiFallbackProvider implements FallbackProvider {

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		log.error("服务不可用异常---route:{},exceptionType:{},stackTrace:{}", route, cause.getClass().getName(), cause.getStackTrace());
		String message = "";
		try {
			if (cause instanceof HystrixTimeoutException) {
				message = JacksonUtils.bean2Json(R.fail503(String.format("%s timeout", route)));
			} else {
				message = JacksonUtils.bean2Json(R.fail503(String.format("%s Service exception", route)));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return fallbackResponse(message);
	}

	@Override
	public String getRoute() {
		return "*";
	}

	public ClientHttpResponse fallbackResponse(String message) {

		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.SERVICE_UNAVAILABLE;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.SERVICE_UNAVAILABLE.value();
			}

			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
			}

			@Override
			public void close() {

			}

			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream(message.getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}
		};
	}
}
