package com.robert.dto;

import java.sql.Date;

public class VideogameDTO {
	
	private String director;
	private String titulo;
	private String descripcion;
	private Date fecha;
	public VideogameDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VideogameDTO(String director, String titulo, String descripcion, Date fecha) {
		super();
		this.director = director;
		this.titulo = titulo;
		this.fecha = fecha;
		this.descripcion = descripcion;
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
	

}
