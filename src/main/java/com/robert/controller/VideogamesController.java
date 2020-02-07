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

import com.robert.dto.VideogameDTO;
import com.robert.service.VideogameService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
public class VideogamesController {
	@Autowired
	private VideogameService gameService;

	
	@GetMapping("/games")
	public List<VideogameDTO> getAll(){
		return gameService.getAll();
	}
	
	@GetMapping("/games/director/{director}")
	public List<VideogameDTO> getGamesByDirector(@PathVariable String director){
		return gameService.videogamesByDirector(director);
	}
	
	@GetMapping("/games/title/{titulo}")
	public VideogameDTO getGameByTitle(@PathVariable String titulo) {
		return gameService.findByTitle(titulo);
	}
	
	@PostMapping("/games")
	public void newGame(@RequestBody VideogameDTO game) {
		gameService.saveVideogame(game);
	}
	
	@DeleteMapping("/game/{titulo}")
	public void deleteGame(@PathVariable String titulo) {
		gameService.deleteByTitle(titulo);
	}
	
	@PutMapping("/game/{titulo}")
	public void updateGame(@RequestBody VideogameDTO game, @PathVariable String titulo) {
		gameService.updateVideogame(game, titulo);
	}
	
}
