package com.robert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.robert.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	@Query(value = "select * from comentarios where tituloPelicula like ?1", nativeQuery = true)
	List<Comment> getByMovieTitle(String title);

}
