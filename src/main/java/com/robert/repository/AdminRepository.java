package com.robert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robert.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {
	
	
}
