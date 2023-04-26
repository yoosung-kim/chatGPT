package com.project.review.config;

import static java.lang.String.*;

import java.util.Date;

import org.springframework.http.HttpStatus;

import feign.Request;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class FeignErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {

		if (isRetryable(response)) {
			log.warn("{} 요청이 실패하여 재시도를 수행합니다. status: {}, requestUrl: {}, requestBody: {}, responseBody: {}",
				response.status(), methodKey, response.request().url(), response.request().body(), response.body());

			return new RetryableException(response.status(), response.reason(), response.request().httpMethod(), new Date(), response.request());
		}

		return new IllegalStateException(format("%s 요청이 실패했습니다. - cause: %s, headers: %s", methodKey, response.status(), response.headers()));
	}

	private boolean isRetryable(Response response) {
		return isGetRequest(response) && isServerError(response);
	}

	private boolean isGetRequest(Response response) {
		return response.request().httpMethod().equals(Request.HttpMethod.GET);
	}

	private boolean isServerError(Response response) {
		int statusCode = response.status();

		return HttpStatus.valueOf(statusCode).is5xxServerError();
	}
}
