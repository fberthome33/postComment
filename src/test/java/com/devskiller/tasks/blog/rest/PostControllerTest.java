package com.devskiller.tasks.blog.rest;

import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.model.dto.NewPostDto;
import com.devskiller.tasks.blog.model.dto.PostDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostControllerTest extends AbstractControllerTest {

	@Test
	public void shouldAddComment() throws Exception {

		// given
		String postBody = "{\"title\":\"Test title\", \"content\":\"Test content\"}";
		NewPostDto newPost = new NewPostDto("Test title", "Test content");

		// when
		when(postService.createPost(newPost)).thenReturn("1");

		// then
		mockMvc.perform(post("/posts")
				.content(postBody)
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON))
			.andExpect(status().isCreated());
	}

	@Test
	public void shouldReturnFoundPost() throws Exception {
		// given
		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
		PostDto post = new PostDto("Title", "content", creationDate);

		// when
		when(postService.getPost("1")).thenReturn(post);

		// then
		mockMvc.perform(get("/posts/1").accept(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON))
				.andExpect(jsonPath("$.title", is("Title")))
				.andExpect(jsonPath("$.content", is("content")))
				.andExpect(jsonPath("$.creationDate", is(creationDate.toString())));
	}
}
