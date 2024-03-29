package com.robert.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="videogames")
public class Videogame implements Serializable{
	
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
	private String urlImage;
	@NotNull
	private Date fecha;
	@NotNull
	private Double precio;
	@NotNull
	private Integer stock;
	
	public Videogame(String director, String titulo, String descripcion, String url, Date fecha, Double precio, Integer stock) {
		super();
		this.director = director;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.urlImage = url;
		this.fecha = fecha;
		this.precio = precio;
		this.stock = stock;
	}
	public Videogame() {
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
