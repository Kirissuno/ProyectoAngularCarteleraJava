package com.robert.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="carrito")
public class ShoppingCart {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String usuario;
	private String videojuego;
	private Integer cantidad;
	
	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShoppingCart(String usuario, String videojuego, Integer cantidad) {
		super();
		this.usuario = usuario;
		this.videojuego = videojuego;
		this.cantidad = cantidad;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getVideojuego() {
		return videojuego;
	}
	public void setVideojuego(String videojuego) {
		this.videojuego = videojuego;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
