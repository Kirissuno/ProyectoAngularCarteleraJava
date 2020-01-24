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

import com.robert.dto.AdminDTO;
import com.robert.service.AdminService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
public class AdminController {
	
	@Autowired
	private AdminService aservice;
	
	
	@GetMapping("/filmografia/admins")
	public List<AdminDTO> getAllAdmins(){
		return aservice.getAllAdmins();
	}
	
	@GetMapping("/filmografia/admin/{usuario}")
	public AdminDTO getAdmin(@PathVariable String usuario) {
		return aservice.getAdmin(usuario);
	}
	
	@PostMapping("/filmografia/admins")
	public void addAdmin(@RequestBody AdminDTO admin) {
		aservice.addAdmin(admin);
	}
	
	@DeleteMapping("/filmografia/admin/d/{usuario}")
	public void deleteAdmin(@PathVariable String usuario) {
		aservice.deleteAdmin(usuario);
	}

}
