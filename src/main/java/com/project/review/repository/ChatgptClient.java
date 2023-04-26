package com.project.review.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.review.config.ChatgptAuthHeaderConfig;
import com.project.review.model.ChatgptRequest;
import com.project.review.model.ChatgptResponse;

@FeignClient(name = "chatgpt", url = "${apis.chatgpt.domain}", configuration = {ChatgptAuthHeaderConfig.class})
public interface ChatgptClient {

	@PostMapping("/v1/chat/completions")
	ChatgptResponse sendToChatgpt(@RequestBody ChatgptRequest chatgptRequest);
}
