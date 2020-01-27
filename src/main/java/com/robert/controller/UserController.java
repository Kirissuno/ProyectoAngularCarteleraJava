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

import com.robert.dto.AdminDTO;
import com.robert.dto.UserDTO;
import com.robert.model.Roles;
import com.robert.service.AdminService;
import com.robert.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private AdminService aService;
	@Autowired
	private UserService uService;
	
	
	@GetMapping("/movies/admins")
	public List<AdminDTO> getAllAdmins(){
		return aService.getAllAdmins();
	}
	
	@GetMapping("/movies/admin/{usuario}")
	public AdminDTO getAdmin(@PathVariable String usuario) {
		return aService.getAdmin(usuario);
	}
	
	@PostMapping("/movies/admins")
	public void addAdmin(@RequestBody AdminDTO admin) {
		aService.addAdmin(admin);
	}
	
	@DeleteMapping("/movies/admin/{usuario}")
	public void deleteAdmin(@PathVariable String usuario) {
		aService.deleteAdmin(usuario);
	}
	
	@GetMapping("/movies/users")
	public List<UserDTO> getAllUsers() {
		uService.addUser(new UserDTO("robert", "robert", Roles.MODERATOR));
		return uService.getAllUsers();
	}
	
	@GetMapping("/movies/user/{usuario}")
	public UserDTO getUser(@PathVariable String usuario) {
		return uService.getUser(usuario);
	}
	
	@PutMapping("/movies/user/{usuario}")
	public void updateUser(@RequestBody UserDTO user) {
		uService.updateUser(user);
	}
	
	@PostMapping("/movies/users")
	public void addUser(@RequestBody UserDTO user) {
		uService.addUser(user);
	}
	
	@DeleteMapping("/movies/user/{usuario}")
	public void deleteUser(@PathVariable String usuario) {
		aService.deleteUser(usuario);
	}
	
	@PostMapping("/movies/mods")
	public void addModerator(@RequestBody UserDTO mod) {
		aService.addModerator(mod);
	}
	
	@GetMapping("/movies/mods")
	public List<UserDTO> getAllMods(){
		return aService.getAllMods();
	}
	
	@GetMapping("/movies/mod/{usuario}")
	public UserDTO getModerator(@PathVariable String usuario) {
		return aService.getModerator(usuario);
	}
	
	@DeleteMapping("/movies/mod/{usuario}")
	public void deleteModerator(@PathVariable String usuario) {
		aService.deleteModerator(usuario);
	}

	@PutMapping("/movies/mod/{usuario}")
	public void updateModerator(@RequestBody UserDTO mod) {
		aService.updateModerator(mod);
	}
	
}
