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
	@NotEmpty
	@NotNull
	private String director;
	@Id
	@NotNull
	@NotEmpty
	@Column(length = 50)
	private String titulo;
	@NotEmpty
	@NotNull
	private String description;
	@NotNull
	private Date fecha;
	
	public Movie(String director, String titulo, Date fecha) {
		super();
		this.director = director;
		this.titulo = titulo;
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

}
