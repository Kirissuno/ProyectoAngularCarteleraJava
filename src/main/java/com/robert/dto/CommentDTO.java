package com.robert.dto;

public class CommentDTO {
	private String titulo;
	private String comentario;
	private String usuario;
	
	public CommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentDTO(String titulo, String comentario, String usuario) {
		super();
		this.titulo = titulo;
		this.comentario = comentario;
		this.usuario = usuario;
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
	@Override
	public String toString() {
		return "Comentario [titulo=" + titulo + ", comentario=" + comentario + ", usuario=" + usuario + "]";
	}
}
