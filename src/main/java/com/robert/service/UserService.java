package com.robert.service;

import java.util.List;

import com.robert.dto.UserDTO;

public interface UserService {
	
	UserDTO getUser(String usuario);
	List<UserDTO> getAllUsers();
	void updateUser(UserDTO usuario);
	void addUser(UserDTO usuario);
	UserDTO getAdmin(UserDTO admin);
	void deleteUser(String user);

}
