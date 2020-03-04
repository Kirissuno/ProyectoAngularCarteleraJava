package com.robert.dto;

import java.sql.Date;

public class VideogameDTO {
	
	private String director;
	private String titulo;
	private String descripcion;
	private String urlImage;
	private Date fecha;
	private Double precio;
	private Integer stock;
	
	public VideogameDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VideogameDTO(String director, String titulo, String descripcion, String url, Date fecha, Double precio, Integer stock) {
		super();
		this.director = director;
		this.titulo = titulo;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.urlImage = url;
		this.precio = precio;
		this.stock = stock;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
	
	

}
