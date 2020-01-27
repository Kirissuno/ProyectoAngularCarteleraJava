package com.robert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robert.dto.MovieDTO;
import com.robert.service.MovieService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
public class MovieController {
	@Autowired
	private MovieService pService;

	
	@GetMapping("/movies")
	public List<MovieDTO> getAll(){
		return pService.getAll();
	}
	
	@GetMapping("/movies/director/{director}")
	public List<MovieDTO> getMovieByDirector(@PathVariable String director){
		return pService.pelisDirector(director);
	}
	
	@GetMapping("/movies/title/{titulo}")
	public MovieDTO getPeliByTitle(@PathVariable String titulo) {
		return pService.findByTitulo(titulo);
	}
	
	@PostMapping("/movies")
	public void nuevaMovie(@RequestBody MovieDTO pelicula) {
		pService.createNewPeli(pelicula);
	}
	
	@DeleteMapping("/movie/{titulo}")
	public void deleteMovie(@PathVariable String titulo) {
		pService.deleteByTitulo(titulo);
	}
	
	@PutMapping("/movie/{titulo}")
	public void updateMovie(@RequestBody MovieDTO pelicula, @PathVariable String titulo) {
		pService.updatePeli(pelicula, titulo);
	}
	
}
