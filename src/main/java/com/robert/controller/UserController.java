package com.robert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robert.dto.UserDTO;
import com.robert.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserService uService;
	
	@GetMapping("/games/users")
	public List<UserDTO> getAllUsers() {
		return uService.getAllUsers();
	}
	
	@GetMapping("/games/user/{usuario}")
	public UserDTO getUser(@PathVariable String usuario) {
		return uService.getUser(usuario);
	}
	
	@PutMapping("/games/user/{usuario}")
	public void updateUser(@RequestBody UserDTO user) {
		uService.updateUser(user);
	}
	
	@PostMapping("/games/users")
	public void addUser(@RequestBody UserDTO user) {
		uService.addUser(user);
	}
	
	@DeleteMapping("/games/users/{usuario}")
	public void deleteUser(@PathVariable String usuario) {
		uService.deleteUser(usuario);
	}
	
}
