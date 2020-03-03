package com.robert.service;

import java.util.List;

import com.robert.dto.UserDTO;
import com.robert.dto.VideogameDTO;
import com.robert.model.ShoppingCart;

public interface ShoppingCartService {

	void addGame(VideogameDTO game, UserDTO user);
	void addOneMore(VideogameDTO game, UserDTO user);
	void removeOne(VideogameDTO game, UserDTO user);
	void removeGame(VideogameDTO game, UserDTO user);
	List<ShoppingCart> getCarts();
	List<ShoppingCart> getCartByUser(UserDTO user);

}
