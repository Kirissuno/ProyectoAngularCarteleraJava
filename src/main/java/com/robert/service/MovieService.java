package com.robert.service;

import java.util.List;

import com.robert.dto.MovieDTO;

public interface MovieService {
	public List<MovieDTO> getAll();
	public MovieDTO findByTitulo(String titulo);
	public void createNewPeli(MovieDTO pelicula);
	public void deleteByTitulo(String titulo);
	public void updatePeli(MovieDTO pelicula, String titulo);
	public List<MovieDTO> pelisDirector(String director);
}
