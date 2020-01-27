package com.robert.service;


import java.util.List;

import com.robert.dto.AdminDTO;
import com.robert.dto.UserDTO;

public interface AdminService {

	List<AdminDTO> getAllAdmins();
	AdminDTO getAdmin(String usuario);
	void addAdmin(AdminDTO admin);
	void deleteAdmin(String usuario);
	
	void deleteUser(String usuario);
	
	void addModerator(UserDTO moderator);
	List<UserDTO> getAllMods();
	void deleteModerator(String moderator);
	void updateModerator(UserDTO user);
	UserDTO getModerator(String user);
	
}
