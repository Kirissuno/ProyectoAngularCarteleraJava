package com.robert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.robert.model.Movie;
import com.robert.model.Roles;
import com.robert.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	@Query(value = "select * from users where rol like ?1", nativeQuery = true)
	List<Movie> getUsersByRole(Roles rol);

}
