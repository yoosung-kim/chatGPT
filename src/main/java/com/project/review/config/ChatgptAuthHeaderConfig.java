package com.project.review.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

public class ChatgptAuthHeaderConfig {
	@Value("${apis.chatgpt.apiKey}")
	String apiKey;

	@Bean
	public RequestInterceptor chatgptAuthRequestInterceptor() {
		return requestTemplate -> requestTemplate.header("Authorization", "Bearer " + apiKey);
	}
}
