package com.robert.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robert.dto.AdminDTO;
import com.robert.dto.UserDTO;
import com.robert.exception.ResourceNotFoundException;
import com.robert.model.Admin;
import com.robert.repository.AdminRepository;
import com.robert.repository.UserRepository;
import com.robert.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<AdminDTO> getAllAdmins() {
		List<Admin> allAdmins = adminRepository.findAll();
		List<AdminDTO> allAdminsDTO = new ArrayList<AdminDTO>();
		for(Admin admin : allAdmins) {
			allAdminsDTO.add(new AdminDTO(admin.getUsuario(), admin.getContrasena()));
		}
		return allAdminsDTO;
	}

	@Override
	public AdminDTO getAdmin(String usuario) {
		Admin admin = adminRepository.findById(usuario).get();
		return new AdminDTO(admin.getUsuario(), admin.getContrasena());
	}

	@Override
	public void addAdmin(AdminDTO admin) {
		adminRepository.save(new Admin(admin.getUsuario(), admin.getContrasena()));		
	}

	@Override
	public void deleteAdmin(String usuario) {
		adminRepository.delete(adminRepository.findById(usuario).get());		
	}

	@Override
	public void deleteUser(String usuario) {
		userRepository.delete(userRepository.findById(usuario).get());
	}

	@Override
	public void updateUser(UserDTO usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addModerator(UserDTO moderator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserDTO> getAllMods() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getModerator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteModerator(String moderator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateModerator(UserDTO user) {
		// TODO Auto-generated method stub
		
	}
	
	

}
