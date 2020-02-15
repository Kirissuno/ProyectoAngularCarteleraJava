package com.robert.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robert.dto.CommentDTO;
import com.robert.model.Comment;
import com.robert.repository.CommentRepository;
import com.robert.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public void addComment(CommentDTO comment) {
		commentRepository.save(new Comment(comment.getTitulo(), comment.getComentario(), comment.getUsuario()));
	}

	@Override
	public void deleteComment(Integer commentID) {
		Optional<Comment> comm = commentRepository.findById(commentID);
		if(comm.isPresent()) {
			commentRepository.delete(comm.get());
		}
	}

	@Override
	public List<CommentDTO> getAllComments() {
		List<Comment> commentsBD = commentRepository.findAll();
		List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();
		for(Comment comm : commentsBD) {
			commentsDTO.add(new CommentDTO(comm.getTitulo(), comm.getComentario(), comm.getUsuario(), comm.getId()));
		}
		return commentsDTO;
	}
	
	@Override
	public List<CommentDTO> getAllCommentsByGame(String gameTitle){
		List<Comment> commentsBD = commentRepository.getByGameTitle(gameTitle);
		List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();
		for(Comment comm : commentsBD) {
			commentsDTO.add(new CommentDTO(comm.getTitulo(), comm.getComentario(), comm.getUsuario(), comm.getId()));
		}
		return commentsDTO;
	}

	@Override
	public List<CommentDTO> getLast3Comments() {
		List<Comment> commentsBD = commentRepository.getLast3Comments();
		List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();
		for(Comment comm : commentsBD) {
			commentsDTO.add(new CommentDTO(comm.getTitulo(), comm.getComentario(), comm.getUsuario(), comm.getId()));
		}
		return commentsDTO;
	}

}
