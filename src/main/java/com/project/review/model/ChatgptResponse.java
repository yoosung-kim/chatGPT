package com.project.review.model;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;

@Getter
public class ChatgptResponse {
	private String id;
	private String object;
	private String model;
	private long created;
	private Usage usage;
	private List<Choice> choices;
}
