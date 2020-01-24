package com.robert.service;

import com.robert.dto.UserDTO;

public interface UserService {
	
	UserDTO getUser(String usuario);
	void updateUser(UserDTO usuario);
	void addUser(UserDTO usuario);

}
