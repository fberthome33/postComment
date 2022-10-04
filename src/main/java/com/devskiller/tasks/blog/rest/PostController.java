package com.devskiller.tasks.blog.rest;

import com.devskiller.tasks.blog.model.dto.NewPostDto;
import com.devskiller.tasks.blog.model.dto.PostDto;
import com.devskiller.tasks.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping(value = "/{postId}")
	@ResponseStatus(HttpStatus.OK)
	public PostDto getPost(@PathVariable final String postId) {
		return postService.getPost(postId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> createPost(@RequestBody final NewPostDto newPostDto) {
		String postId = postService.createPost(newPostDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(postId);
	}
}
