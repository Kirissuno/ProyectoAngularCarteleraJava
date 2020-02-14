package com.robert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.robert.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	@Query(value = "select * from comments where titulo_pelicula like ?1", nativeQuery = true)
	List<Comment> getByGameTitle(String title);
	
	@Query(value = "select * from comments order by id desc limit 3", nativeQuery = true)
	List<Comment> getLast3Comments();

}
