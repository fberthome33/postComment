package com.devskiller.tasks.blog.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Comment {

	@Id
	private String id;

	private String postId;

	private String comment;

	private String author;

	private LocalDateTime creationDate;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId)  { this.postId = postId;}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
