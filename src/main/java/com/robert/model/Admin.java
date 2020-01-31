package com.robert.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

@Entity
@Table(name="admins")
public class Admin implements Serializable {
	
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
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(String usuario, String contrasena) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
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
	
	

}
