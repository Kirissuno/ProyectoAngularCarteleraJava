package com.robert.dto;

public class CommentDTO {
	private Integer id;
	private String titulo;
	private String comentario;
	private String usuario;
	
	public CommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentDTO(String titulo, String comentario, String usuario, Integer id) {
		super();
		this.titulo = titulo;
		this.comentario = comentario;
		this.usuario = usuario;
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
		return "Comentario [titulo=" + titulo + ", comentario=" + comentario + ", usuario=" + usuario + "]";
	}
}
