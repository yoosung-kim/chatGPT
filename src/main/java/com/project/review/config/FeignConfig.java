package com.project.review.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

import feign.Retryer;

@Configuration
@EnableFeignClients("com.project.review")
public class FeignConfig {
	@Bean
	public FeignFormatterRegistrar dateTimeFormatterRegistrar() {
		return registry -> {
			DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
			registrar.setUseIsoFormat(true);
			registrar.registerFormatters(registry);
		};
	}
	@Bean
	public Retryer retryer() {
		return new Retryer.Default(500L, TimeUnit.SECONDS.toMillis(2L), 3);
	}

	@Bean
	public FeignErrorDecoder decoder() {
		return new FeignErrorDecoder();
	}
}
