package com.robert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="comments")
public class Comment  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
	
	@NotNull
	@Column(length = 50)
	private String tituloPelicula;
	@NotNull
	@Column(length = 100)
	private String comentario;
	@NotNull
	private String usuario;
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment( String titulo,  String comentario,  String usuario) {
		super();
		this.tituloPelicula = titulo;
		this.comentario = comentario;
		this.usuario = usuario;
	}
	public String getTitulo() {
		return tituloPelicula;
	}
	public void setTitulo(String titulo) {
		this.tituloPelicula = titulo;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Comentario [titulo=" + tituloPelicula + ", comentario=" + comentario + ", usuario=" + usuario + "]";
	}
	
	
	
}
