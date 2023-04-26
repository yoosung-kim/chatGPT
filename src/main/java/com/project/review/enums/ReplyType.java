package com.project.review.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReplyType {
	NORMAL(""),
	FRIENDLY("친근한 말투로 부탁해"),
	POLITELY("깍듯한 말투로 부탁해"),
	IMOTICON("이모티콘 넣어서 부탁해");

	private String textByType;
}
