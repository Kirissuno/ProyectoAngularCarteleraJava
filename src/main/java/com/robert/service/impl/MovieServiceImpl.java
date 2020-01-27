package com.robert.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robert.dto.MovieDTO;
import com.robert.exception.ResourceNotFoundException;
import com.robert.model.Movie;
import com.robert.repository.MovieRepository;
import com.robert.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository prepo;
	
	@Override
	public List<MovieDTO> getAll() {
		List<Movie> pelisBD = prepo.findAll();
		List<MovieDTO> pelisDTO = new ArrayList<MovieDTO>();
		for(Movie peli : pelisBD) {
			pelisDTO.add(new MovieDTO(peli.getDirector(), peli.getTitulo(), peli.getFecha()));
		}
		return pelisDTO;
	}

	@Override
	public MovieDTO findByTitulo(String titulo) {
		Movie peliBD = prepo.findById(titulo).orElseThrow(() -> new ResourceNotFoundException("Pelicula a buscar no existe"));
		return new MovieDTO(peliBD.getDirector(), peliBD.getTitulo(), peliBD.getFecha());
	}

	@Override
	public void createNewPeli(MovieDTO pelicula) {
		Movie peliACrear = new Movie(pelicula.getDirector(), pelicula.getTitulo(), pelicula.getFecha());		
		if(prepo.findById(peliACrear.getTitulo()).isPresent()) {
			throw new ResourceNotFoundException("Pelicula con ese titulo ya existente");
		}else {
			prepo.save(peliACrear);
		}
	}

	@Override
	public void deleteByTitulo(String titulo) {
		Optional<Movie> peliBD = prepo.findById(titulo);
		if(peliBD.isPresent()) {
			Movie peliABorrar = peliBD.get();
			prepo.delete(peliABorrar);
		}else {
			throw new ResourceNotFoundException("Pelicula a borrar inexistente");
		}
	}

	@Override
	public void updatePeli(MovieDTO pelicula, String titulo) {		
		if(prepo.findById(titulo).isPresent()) {
			Movie peliupdate = prepo.findById(titulo).get();
			peliupdate.setDirector(pelicula.getDirector());
			peliupdate.setFecha(pelicula.getFecha());
			prepo.save(peliupdate);
		}else {
			throw new ResourceNotFoundException("Pelicula a actualizar inexistente");
		}
	}

	@Override
	public List<MovieDTO> pelisDirector(String director) {
		List<Movie> pelisBD = prepo.getMoviesByDirector(director);
		List<MovieDTO> pelisADevolver = new ArrayList<MovieDTO>();
		for(Movie peli : pelisBD) {
			pelisADevolver.add(new MovieDTO(peli.getDirector(), peli.getTitulo(), peli.getFecha()));
		}
		return pelisADevolver;
	}

}
