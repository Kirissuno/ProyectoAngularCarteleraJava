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
public class PeliculaController {
	@Autowired
	private MovieService pservice;

	
	@GetMapping("/filmografia")
	public List<MovieDTO> getAll(){
		return pservice.getAll();
	}
	
	@GetMapping("/filmografias/{director}")
	public List<MovieDTO> getPelisDirector(@PathVariable String director){
		return pservice.pelisDirector(director);
	}
	
	@GetMapping("/filmografia/{titulo}")
	public MovieDTO getPeliByTitulo(@PathVariable String titulo) {
		return pservice.findByTitulo(titulo);
	}
	
	@PostMapping("/filmografia")
	public void nuevaPeli(@RequestBody MovieDTO pelicula) {
		pservice.createNewPeli(pelicula);
	}
	
	@DeleteMapping("/filmografia/{titulo}")
	public void deletePeli(@PathVariable String titulo) {
		pservice.deleteByTitulo(titulo);
	}
	
	@PutMapping("/filmografia/{titulo}")
	public void updatePeli(@RequestBody MovieDTO pelicula, @PathVariable String titulo) {
		pservice.updatePeli(pelicula, titulo);
	}
	
}
