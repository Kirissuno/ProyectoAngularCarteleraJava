package com.robert.service;

import java.util.List;

import com.robert.model.ShoppingCart;

public interface ShoppingCartService {

	void addGame(String game, String user);
	void addOneMore(String game, String user);
	void removeOne(String game, String user);
	void removeGame(String game, String user);
	List<ShoppingCart> getCarts();
	List<ShoppingCart> getCartByUser(String user);
	ShoppingCart getCartByUserAndGame(String user, String game);

}
