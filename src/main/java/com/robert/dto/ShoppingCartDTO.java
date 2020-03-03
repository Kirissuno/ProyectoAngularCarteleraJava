package com.robert.dto;

public class ShoppingCartDTO {
	
	private String usuario;
	private String videojuego;
	private Integer cantidad;
	public ShoppingCartDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShoppingCartDTO(String usuario, String videojuego, Integer cantidad) {
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
