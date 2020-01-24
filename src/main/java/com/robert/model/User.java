package com.robert.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
	@NotEmpty
	@Column(length = 50)
	private String usuario;
	@NotNull
	@NotEmpty
	private String contrasena;
	@NotNull
	@NotEmpty
	private Roles rol;
	
	public User(@NotEmpty String usuario, @NotEmpty String contrasena, @NotEmpty Roles rol) {
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
	public Roles getRol() {
		return rol;
	}
	public void setRol(Roles rol) {
		this.rol = rol;
	}
	
	@Override
	public String toString() {
		return "User [usuario=" + usuario + ", contrasena=" + contrasena + ", rol=" + rol + "]";
	}
	
	

}
