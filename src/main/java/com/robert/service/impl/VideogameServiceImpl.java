package com.robert.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robert.dto.VideogameDTO;
import com.robert.exception.ResourceNotFoundException;
import com.robert.model.Videogame;
import com.robert.repository.MovieRepository;
import com.robert.service.VideogameService;

@Service
public class VideogameServiceImpl implements VideogameService {

	@Autowired
	private MovieRepository gameRepo;
	
	@Override
	public List<VideogameDTO> getAll() {
		List<Videogame> pelisBD = gameRepo.findAll();
		List<VideogameDTO> pelisDTO = new ArrayList<VideogameDTO>();
		for(Videogame peli : pelisBD) {
			pelisDTO.add(new VideogameDTO(peli.getDirector(), peli.getTitulo(), peli.getDescription(), peli.getFecha()));
		}
		return pelisDTO;
	}

	@Override
	public VideogameDTO findByTitle(String titulo) {
		Videogame gameBD = gameRepo.findById(titulo).orElseThrow(() -> new ResourceNotFoundException("Juego a buscar no existe"));
		return new VideogameDTO(gameBD.getDirector(), gameBD.getTitulo(), gameBD.getDescription(), gameBD.getFecha());
	}

	@Override
	public void saveVideogame(VideogameDTO videogame) {
		Videogame newGame = new Videogame(videogame.getDirector(), videogame.getDescripcion(), videogame.getTitulo(), videogame.getFecha());		
		if(gameRepo.findById(newGame.getTitulo()).isPresent()) {
			throw new ResourceNotFoundException("Juego con ese titulo ya existente");
		}else {
			gameRepo.save(newGame);
		}
	}

	@Override
	public void deleteByTitle(String titulo) {
		Optional<Videogame> gameBD = gameRepo.findById(titulo);
		if(gameBD.isPresent()) {
			Videogame peliABorrar = gameBD.get();
			gameRepo.delete(peliABorrar);
		}else {
			throw new ResourceNotFoundException("Juego a borrar inexistente");
		}
	}

	@Override
	public void updateVideogame(VideogameDTO videogame, String title) {
		if(gameRepo.findById(title).isPresent()) {
			Videogame updateGame = gameRepo.findById(title).get();
			updateGame.setDirector(videogame.getDirector());
			updateGame.setFecha(videogame.getFecha());
			updateGame.setDescription(videogame.getDescripcion());
			gameRepo.save(updateGame);
		}else {
			throw new ResourceNotFoundException("Juego a actualizar inexistente");
		}
	}

	@Override
	public List<VideogameDTO> videogamesByDirector(String director) {
		List<Videogame> gameBD = gameRepo.getVideogamesByDirector(director);
		List<VideogameDTO> games= new ArrayList<VideogameDTO>();
		for(Videogame peli : gameBD) {
			games.add(new VideogameDTO(peli.getDirector(), peli.getTitulo(), peli.getDescription(), peli.getFecha()));
		}
		return games;
	}

}
