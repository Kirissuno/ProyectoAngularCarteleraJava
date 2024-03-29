package com.robert.service;

import java.util.List;

import com.robert.dto.CommentDTO;

public interface CommentService {
	
	void addComment(CommentDTO comment);
	List<CommentDTO> getAllComments();
	List<CommentDTO> getLast3Comments();
	List<CommentDTO> getAllCommentsByGame(String gameTitle);
	void deleteComment(Integer commentID);

}
