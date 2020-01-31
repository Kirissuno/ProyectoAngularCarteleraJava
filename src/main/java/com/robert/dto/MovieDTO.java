package com.robert.dto;

import java.sql.Date;

public class MovieDTO {
	
	private String director;
	private String titulo;
	private String descripcion;
	private Date fecha;
	public MovieDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MovieDTO(String director, String titulo, String descripcion, Date fecha) {
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
	
	public String getDescription() {
		return descripcion;
	}
	public void setDescription(String description) {
		this.descripcion = description;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

}
