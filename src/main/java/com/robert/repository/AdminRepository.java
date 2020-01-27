package com.robert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robert.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
	
	
}
