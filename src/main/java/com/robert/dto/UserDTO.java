package com.robert.dto;

public class UserDTO {
	
	private String usuario;
	private String contrasena;
	private String rol;
	
	public UserDTO(String usuario, String contrasena, String rol) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.rol = rol;
	}
	
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	@Override
	public String toString() {
		return "User [usuario=" + usuario + ", contrasena=" + contrasena + ", rol=" + rol + "]";
	}
	
	

}
