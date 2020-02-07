package com.robert.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(length = 50)
	private String usuario;
	@NotNull
	private String contrasena;
	@NotNull
	private String rol;
	
	public User(String usuario, String contrasena, String rol) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.rol = rol;
	}
	
	public User() {
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
