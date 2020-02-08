package com.robert.service;

import java.util.List;

import com.robert.dto.VideogameDTO;

public interface VideogameService {
	List<VideogameDTO> getAll();
	VideogameDTO findByTitle(String titulo);
	void saveVideogame(VideogameDTO videogame);
	void deleteByTitle(String titulo);
	void updateVideogame(VideogameDTO videogame, String title);
	List<VideogameDTO> videogamesByDirector(String director);
	List<VideogameDTO> getUpcommingGames();
}
