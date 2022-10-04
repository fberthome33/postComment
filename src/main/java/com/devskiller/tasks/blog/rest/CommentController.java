package com.devskiller.tasks.blog.rest;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.model.dto.PostDto;
import com.devskiller.tasks.blog.service.CommentService;
import com.devskiller.tasks.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/posts")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping(value = "/{postId}/comments")
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDto> getComments(@PathVariable final String postId) {
		return commentService.getCommentsForPost(postId);
	}

	@PostMapping(value = "/{postId}/comments")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> addComment(@PathVariable final String postId, @RequestBody final NewCommentDto commentDto) {
		String commentId= commentService.addComment(postId, commentDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(commentId);
	}
}
