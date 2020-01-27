package com.robert.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robert.dto.AdminDTO;
import com.robert.dto.UserDTO;
import com.robert.model.Admin;
import com.robert.model.Roles;
import com.robert.model.User;
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
		if(adminRepository.findById(usuario).isPresent()) {
			return new AdminDTO(adminRepository.findById(usuario).get().getUsuario(), adminRepository.findById(usuario).get().getContrasena());
		}else {
			return null;
		}
	}

	@Override
	public void addAdmin(AdminDTO admin) {
		if(!adminRepository.findById(admin.getUsuario()).isPresent()) {
			adminRepository.save(new Admin(admin.getUsuario(), admin.getContrasena()));		
		}
	}

	@Override
	public void deleteAdmin(String usuario) {
		Optional<Admin> userBorrar = adminRepository.findById(usuario);
		if(userBorrar.isPresent()) {
			adminRepository.delete(userBorrar.get());		
		}
	}

	@Override
	public void deleteUser(String usuario) {
		userRepository.delete(userRepository.findById(usuario).get());
	}

	@Override
	public void addModerator(UserDTO moderator) {
		if(!userRepository.findById(moderator.getUsuario()).isPresent()) {
			User newMod = new User(moderator.getUsuario(), moderator.getContrasena(), Roles.MODERATOR);
			userRepository.save(newMod);		
		}
	}

	@Override
	public List<UserDTO> getAllMods() {
		List<User> modsBD = userRepository.findAll();
		List<UserDTO> mods = new ArrayList<UserDTO>();
		for(User mod : modsBD) {
			if(mod.getRol().toString().equals(Roles.MODERATOR.toString())) {
				mods.add(new UserDTO(mod.getUsuario(), mod.getContrasena(), mod.getRol()));
			}
		}
		return mods;
	}

	@Override
	public UserDTO getModerator(String user) {
		Optional<User> mod = userRepository.findById(user);
		if(mod.isPresent() && mod.get().getRol().toString().equals(Roles.MODERATOR.toString())) {
			return new UserDTO(mod.get().getUsuario(), mod.get().getContrasena(), mod.get().getRol());
		}else {
			return null;
		}
	}

	@Override
	public void deleteModerator(String moderator) {
		Optional<User> mod = userRepository.findById(moderator);
		if(mod.isPresent() && mod.get().getRol().toString().equals(Roles.MODERATOR.toString())) {
			userRepository.delete(mod.get());
		}
	}

	@Override
	public void updateModerator(UserDTO user) {
		Optional<User> userBD = userRepository.findById(user.getUsuario());
		if(userBD.isPresent() && userBD.get().getRol().toString().equals(Roles.MODERATOR.toString())) {
			User userUpdate = userBD.get();
			userUpdate.setContrasena(user.getContrasena());
			userUpdate.setRol(user.getRol());
			userRepository.save(userUpdate);
		}
	}

}
