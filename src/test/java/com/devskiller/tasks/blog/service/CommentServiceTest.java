package com.devskiller.tasks.blog.service;

import com.devskiller.tasks.blog.model.Post;
import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class CommentServiceTest {
	@Autowired
	PostRepository postRepository;

	@Autowired
	CommentService commentService;

	@Test
	public void shouldCreateComment() {
		Post post = createTestPost();
		NewCommentDto comment = new NewCommentDto();
		comment.setAuthor("Author");
		comment.setContent("Content");
		String commentId = commentService.addComment(post.getId(), comment);

		assertThat("Comment id shouldn't be null", commentId, notNullValue());
	}

	@Test
	public void shouldNotCreateCommentAndThrowException() {
		String nonExistingPostId = "10ade";
		Exception exception = assertThrows(RuntimeException.class, () -> {
			NewCommentDto comment = new NewCommentDto();
			comment.setAuthor("Author");
			comment.setContent("Content");
			String commentId = commentService.addComment(nonExistingPostId, comment);
		});

		String expectedMessage = "Inexisting Post with Id 10ade";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	private Post createTestPost() {
		Post post = new Post();
		post.setTitle("Test title");
		post.setContent("Test content");
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		post.setCreationDate(creationDate);
		postRepository.save(post);
		return post;
	}

	@Test
	public void shouldReturnAddedComment() {
		Post post = createTestPost();

		NewCommentDto comment = new NewCommentDto();
		comment.setAuthor("Author");
		comment.setContent("Content");

		commentService.addComment(post.getId(), comment);

		List<CommentDto> comments = commentService.getCommentsForPost(post.getId());

		assertThat("There should be one comment", comments, hasSize(1));
		assertThat(comments.get(0).getAuthor(), equalTo("Author"));
		assertThat(comments.get(0).getComment(), equalTo("Content"));
	}
}
