package com.robert.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robert.dto.UserDTO;
import com.robert.model.Roles;
import com.robert.model.User;
import com.robert.repository.UserRepository;
import com.robert.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDTO getUser(String usuario) {
		Optional<User> userBD = userRepository.findById(usuario);
		if(userBD.isPresent()) {
			return new UserDTO(userBD.get().getUsuario(), userBD.get().getContrasena(), userBD.get().getRol());
		}else {
			return null;
		}
	}

	@Override
	public void updateUser(UserDTO usuario) {
		Optional<User> userBD = userRepository.findById(usuario.getUsuario());
		if(userBD.isPresent()) {
			User userUpdate = userBD.get();
			userUpdate.setContrasena(usuario.getContrasena());
			userUpdate.setRol(usuario.getRol());
			userRepository.save(userUpdate);
		}
	}

	
	/**
	 * No funca
	 */
	@Override
	public void addUser(UserDTO usuario) {
		Optional<User> userBD = userRepository.findById(usuario.getUsuario());
		if(!userBD.isPresent()) {
			Integer rol = null;
			if(userBD.get().getRol().toString().equals(Roles.NON_REGISTRED.toString())) {
				rol = 0;
			}else if(userBD.get().getRol().toString().equals(Roles.REGISTRED.toString())) {
				rol = 1;
			}else if(userBD.get().getRol().toString().equals(Roles.MODERATOR.toString())) {
				rol = 2;
			}
			
			userRepository.save(new User(userBD.get().getUsuario(), userBD.get().getContrasena(), rol));
		}
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> usersBD = userRepository.findAll();
		List<UserDTO> users = new ArrayList<UserDTO>();
		for(User user : usersBD) {
			users.add(new UserDTO(user.getUsuario(), user.getContrasena(), user.getRol()));
		}
		return users;
	}

}
