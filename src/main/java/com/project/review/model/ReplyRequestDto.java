package com.project.review.model;

import com.project.review.enums.ReplyType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ReplyRequestDto {
	private ReplyType replyType;
	private String review;
}
