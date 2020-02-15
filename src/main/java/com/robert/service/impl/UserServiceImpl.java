package com.robert.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robert.dto.UserDTO;
import com.robert.exception.ResourceNotFoundException;
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

	@Override
	public void addUser(UserDTO usuario) {
		Optional<User> userBD = userRepository.findById(usuario.getUsuario());
		if(!userBD.isPresent()) {			
			userRepository.save(new User(usuario.getUsuario(), usuario.getContrasena(), usuario.getRol()));
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

	@Override
	public UserDTO getAdmin(UserDTO admin) {
		Integer usersBD = userRepository.isAdmin(admin.getUsuario(), admin.getContrasena());
		if(usersBD > 0) {
			User user = userRepository.findById(admin.getUsuario()).orElseThrow(() -> new ResourceNotFoundException("No es admin"));
			return new UserDTO(user.getUsuario(), user.getContrasena(), user.getRol());
		}else {
			return null;
		}
	}
	
	@Override
	public void deleteUser(String user) {
		userRepository.delete(userRepository.findById(user).orElseThrow(() -> new ResourceNotFoundException("Usuario inexistente")));
	}

}
