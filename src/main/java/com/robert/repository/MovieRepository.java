package com.robert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.robert.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
	
	@Query(value = "select * from movies where director like %?1%", nativeQuery = true)
	List<Movie> getMoviesByDirector(String director);
	
	@Query(value = "select * from movies where fecha < now()", nativeQuery = true)
	List<Movie> getExpiredMovies();
	
	@Query(value = "select * from movies where fecha > now()", nativeQuery = true)
	List<Movie> getValidMovies();
		
}
