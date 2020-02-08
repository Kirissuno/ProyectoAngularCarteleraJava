package com.robert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.robert.model.Videogame;
import com.robert.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	@Query(value = "select * from users where rol like ?1", nativeQuery = true)
	List<Videogame> getUsersByRole(String rol);
	
	@Query(value = "select count(*) from users where usuario like ?1 and password like ?2 and rol like 'admin'", nativeQuery = true)
	Integer isAdmin(String user, String password);

}
