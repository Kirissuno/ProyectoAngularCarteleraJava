package com.robert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robert.dto.CommentDTO;
import com.robert.service.CommentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
public class CommentController {

	@Autowired
	private CommentService cService;
	
	@PostMapping("/movies/comments")
	public void addComment(@RequestBody CommentDTO comment) {
		cService.addComment(comment);
	}
	
	@DeleteMapping("/movies/comment/{comm}")
	public void deleteComment(@RequestBody CommentDTO comm) {
		cService.deleteComment(comm);
	}
	
	@GetMapping("/movies/comments")
	public List<CommentDTO> getAllComments(){
		return cService.getAllComments();
	}
	
	@GetMapping("/movies/comments/{movie}")
	public List<CommentDTO> getAllCommentsByMovie(@PathVariable String movie){
		return cService.getAllCommentsByMovie(movie);
	}
	
	@GetMapping("/movies/comments/last3")
	public List<CommentDTO> getLast3Comments(){
		return cService.getLast3Comments();
	}
	
}
