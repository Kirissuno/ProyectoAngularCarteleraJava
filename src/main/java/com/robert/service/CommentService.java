package com.robert.service;

import java.util.List;

import com.robert.dto.CommentDTO;

public interface CommentService {
	
	void addComment(CommentDTO comment);
	void deleteComment(CommentDTO comment);
	List<CommentDTO> getAllComments();
	List<CommentDTO> getLast3Comments();
	List<CommentDTO> getAllCommentsByMovie(String movieTitle);

}
