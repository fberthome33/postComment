package com.devskiller.tasks.blog.model.dto;

import java.time.LocalDateTime;

public class NewPostDto {

	private final String title;

	private final String content;

	public NewPostDto(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
}
