package com.robert.service;

import java.util.List;

import com.robert.dto.MovieDTO;

public interface MovieService {
	List<MovieDTO> getAll();
	MovieDTO findByTitulo(String titulo);
	void createNewPeli(MovieDTO pelicula);
	void deleteByTitulo(String titulo);
	void updatePeli(MovieDTO pelicula, String titulo);
	List<MovieDTO> pelisDirector(String director);
}
