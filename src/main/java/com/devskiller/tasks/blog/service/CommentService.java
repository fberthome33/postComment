package com.devskiller.tasks.blog.service;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.Post;
import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.repository.CommentRepository;
import com.devskiller.tasks.blog.repository.PostRepository;
import com.sun.jdi.InternalException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

	private final CommentRepository commentRepository;

	private final PostRepository postRepository;

	public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 *
	 * @throws IllegalArgumentException if there is no blog post for passed postId
	 */
	public List<CommentDto> getCommentsForPost(String postId) {
		Optional<Post> existingPost = postRepository.findById(postId);
		if(existingPost.isEmpty()) {
			throw new InternalException("Inexisting Post with Id " + postId);
		}
		return commentRepository.findByPostId(postId).stream().map(comment ->
			new CommentDto(comment.getId(), comment.getComment(), comment.getAuthor(), comment.getCreationDate(), postId)
		).collect(Collectors.toList());
	}

	/**
	 * Creates a new comment
	 *
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if there is no blog post for passed newCommentDto.postId
	 */
	public String addComment(String postId, NewCommentDto newCommentDto) {
		Optional<Post> existingPost = postRepository.findById(postId);
		if(existingPost.isEmpty()) {
			throw new RuntimeException("Inexisting Post with Id " + postId);
		}
		Comment comment = new Comment();
		comment.setComment(newCommentDto.getContent());
		comment.setAuthor(newCommentDto.getAuthor());
		comment.setCreationDate(LocalDateTime.now());
		comment.setPostId(postId);

		Comment commentSaved = commentRepository.save(comment);
		return commentSaved.getId();
	}
}
