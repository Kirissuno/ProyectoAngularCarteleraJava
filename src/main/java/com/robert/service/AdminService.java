package com.robert.service;


import java.util.List;

import com.robert.dto.AdminDTO;
import com.robert.dto.UserDTO;

public interface AdminService {

	public List<AdminDTO> getAllAdmins();
	public AdminDTO getAdmin(String usuario);
	public void addAdmin(AdminDTO admin);
	public void deleteAdmin(String usuario);
	
	public void deleteUser(String usuario);
	public void updateUser(UserDTO usuario);
	
	public void addModerator(UserDTO moderator);
	public List<UserDTO> getAllMods();
	public UserDTO getModerator();
	public void deleteModerator(String moderator);
	public void updateModerator(UserDTO user);
	
}
