package com.robert.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robert.dto.UserDTO;
import com.robert.dto.VideogameDTO;
import com.robert.model.ShoppingCart;
import com.robert.repository.ShoppingCartRepository;
import com.robert.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingRepository;
	
	@Override
	@Transactional
	public void addGame(VideogameDTO game, UserDTO user) {
		shoppingRepository.save(new ShoppingCart(user.getUsuario(), game.getTitulo(), 1));		
	}

	@Override
	@Transactional
	public void addOneMore(VideogameDTO game, UserDTO user) {
		ShoppingCart cart = shoppingRepository.getShoppingCart(game.getTitulo(), user.getUsuario());
		cart.setCantidad(cart.getCantidad()+1);
		shoppingRepository.save(cart);
	}

	@Override
	@Transactional
	public void removeOne(VideogameDTO game, UserDTO user) {
		ShoppingCart cart = shoppingRepository.getShoppingCart(game.getTitulo(), user.getUsuario());
		if(cart.getCantidad() > 1) {
			cart.setCantidad(cart.getCantidad()-1);
			shoppingRepository.save(cart);
		}else {
			removeGame(game, user);
		}
	}

	@Override
	@Transactional
	public void removeGame(VideogameDTO game, UserDTO user) {
		ShoppingCart cart = shoppingRepository.getShoppingCart(game.getTitulo(), user.getUsuario());
		shoppingRepository.delete(cart);
	}

	@Override
	public List<ShoppingCart> getCarts() {
		return shoppingRepository.findAll();
	}

	@Override
	public List<ShoppingCart> getCartByUser(UserDTO user) {
		return shoppingRepository.allSCByUser(user.getUsuario());
	}
	
	

}
