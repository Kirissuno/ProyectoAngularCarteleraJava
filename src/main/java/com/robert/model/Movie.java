package com.robert.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

@Entity
@Table(name="movies")
public class Movie implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	private String director;
	@Id
	@NotNull
	@Column(length = 50)
	private String titulo;
	@NotNull
	private String descripcion;
	@NotNull
	private Date fecha;
	
	public Movie(String director, String titulo, String descripcion, Date fecha) {
		super();
		this.director = director;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fecha = fecha;
	}
	public Movie() {
		super();
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
