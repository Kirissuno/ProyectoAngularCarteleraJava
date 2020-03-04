package com.robert.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robert.dto.VideogameDTO;
import com.robert.exception.ResourceNotFoundException;
import com.robert.model.Videogame;
import com.robert.repository.VideogameRepository;
import com.robert.service.VideogameService;

@Service
public class VideogameServiceImpl implements VideogameService {

	@Autowired
	private VideogameRepository gameRepo;
	
	@Override
	public List<VideogameDTO> getAll() {
		List<Videogame> pelisBD = gameRepo.findAll();
		List<VideogameDTO> pelisDTO = new ArrayList<VideogameDTO>();
		for(Videogame peli : pelisBD) {
			pelisDTO.add(new VideogameDTO(peli.getDirector(), peli.getTitulo(), peli.getDescription(), peli.getUrlImage(), peli.getFecha(), peli.getPrecio(), peli.getStock()));
		}
		return pelisDTO;
	}
	
	@Override
	public List<VideogameDTO> getUpcommingGames() {
		List<Videogame> pelisBD = gameRepo.getValidVideogames();
		List<VideogameDTO> pelisDTO = new ArrayList<VideogameDTO>();
		for(Videogame peli : pelisBD) {
			pelisDTO.add(new VideogameDTO(peli.getDirector(), peli.getTitulo(), peli.getDescription(), peli.getUrlImage(), peli.getFecha(), peli.getPrecio(), peli.getStock()));
		}
		return pelisDTO;
	}
	
	@Override
	public List<VideogameDTO> getLastGames() {
		List<Videogame> pelisBD = gameRepo.getExpiredVideogames();
		List<VideogameDTO> pelisDTO = new ArrayList<VideogameDTO>();
		for(Videogame peli : pelisBD) {
			pelisDTO.add(new VideogameDTO(peli.getDirector(), peli.getTitulo(), peli.getDescription(), peli.getUrlImage(), peli.getFecha(), peli.getPrecio(), peli.getStock()));
		}
		return pelisDTO;
	}

	@Override
	public VideogameDTO findByTitle(String titulo) {
		Optional<Videogame> gameBbDd = gameRepo.findById(titulo);
		if(gameBbDd.isPresent()) {
			Videogame gameBD = gameBbDd.get();
			return new VideogameDTO(gameBD.getDirector(), gameBD.getTitulo(), gameBD.getDescription(), gameBD.getUrlImage(), gameBD.getFecha(), gameBD.getPrecio(), gameBD.getStock());
		}else {
			return null;
		}
	}

	@Override
	@Transactional
	public void saveVideogame(VideogameDTO videogame) {
		Videogame newGame = new Videogame(videogame.getDirector(), videogame.getTitulo(), videogame.getDescripcion(), videogame.getUrlImage(), videogame.getFecha(), videogame.getPrecio(), videogame.getStock());		
		if(gameRepo.findById(newGame.getTitulo()).isPresent()) {
			throw new ResourceNotFoundException("Juego con ese titulo ya existente");
		}else {
			gameRepo.save(newGame);
		}
	}

	@Override
	@Transactional
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
	@Transactional
	public void updateVideogame(VideogameDTO videogame, String title) {
		if(gameRepo.findById(title).isPresent()) {
			Videogame updateGame = gameRepo.findById(title).get();
			updateGame.setDirector(videogame.getDirector());
			updateGame.setFecha(videogame.getFecha());
			updateGame.setDescription(videogame.getDescripcion());
			updateGame.setUrlImage(videogame.getUrlImage());
			updateGame.setPrecio(videogame.getPrecio());
			updateGame.setStock(videogame.getStock());
			gameRepo.save(updateGame);
		}
	}

	@Override
	public List<VideogameDTO> videogamesByDirector(String director) {
		List<Videogame> gameBD = gameRepo.getVideogamesByDirector(director);
		List<VideogameDTO> games= new ArrayList<VideogameDTO>();
		for(Videogame peli : gameBD) {
			games.add(new VideogameDTO(peli.getDirector(), peli.getTitulo(), peli.getDescription(), peli.getUrlImage(), peli.getFecha(), peli.getPrecio(), peli.getStock()));
		}
		return games;
	}

}
