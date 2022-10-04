package com.devskiller.tasks.blog.model.dto;

import java.time.LocalDateTime;

public class CommentDto {

	private String id;

	private String comment;

	private String author;

	private String postId;

	private LocalDateTime creationDate;

	public CommentDto(String id, String comment, String author, LocalDateTime creationDate) {
		this.id = id;
		this.comment = comment;
		this.author = author;
		this.postId = null;
		this.creationDate = creationDate;
	}


	public CommentDto(String id, String comment, String author, LocalDateTime creationDate, String postId) {
		this.id = id;
		this.comment = comment;
		this.author = author;
		this.postId = postId;
		this.creationDate = creationDate;
	}

	public String getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public String getAuthor() {
		return author;
	}

	public String getPostId() {
		return postId;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}
}
