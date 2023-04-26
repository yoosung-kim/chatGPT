package com.project.review.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.review.enums.ReplyType;
import com.project.review.model.ChatgptRequest;
import com.project.review.model.ChatgptResponse;
import com.project.review.model.Message;
import com.project.review.repository.ChatgptClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {
	private final ChatgptClient chatgptClient;

	public String createReviewReply(ReplyType replyType, String review) {
		String chatGptRequestMessage = getChatgptRequestMessage(replyType, review);

		ChatgptRequest request = ChatgptRequest.builder()
				.model("gpt-3.5-turbo")
				.messages(List.of(new Message("user", chatGptRequestMessage)))
				.temperature(0.7)
				.build();

		ChatgptResponse response = chatgptClient.sendToChatgpt(request);
		return response.getChoices().get(0).getMessage().getContent();
	}

	private String getChatgptRequestMessage(ReplyType replyType, String review) {
		StringBuilder sb = new StringBuilder();
		sb.append("나는 배달 전문 음식점 가게 사장님이야. 사용자가 작성한 리뷰에 답글을 달아주려고해. ");
		sb.append("리뷰에 대한 적절한 답글을 만들어줘. ");
		sb.append("내가 누군지와 가게에 대해선 소개할 필요 없어. ");
		sb.append(replyType.getTextByType());
		sb.append("사용자 리뷰는 다음과 같아. ");
		sb.append(review);

		return sb.toString();
	}
}
