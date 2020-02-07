package com.robert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.robert.model.Videogame;

@Repository
public interface MovieRepository extends JpaRepository<Videogame, String> {
	
	@Query(value = "select * from videogames where director like %?1%", nativeQuery = true)
	List<Videogame> getVideogamesByDirector(String director);
	
	@Query(value = "select * from videogames where fecha < now()", nativeQuery = true)
	List<Videogame> getExpiredVideogames();
	
	@Query(value = "select * from videogames where fecha > now()", nativeQuery = true)
	List<Videogame> getValidVideogames();
		
}
