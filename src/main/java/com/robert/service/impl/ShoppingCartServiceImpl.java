package com.robert.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robert.model.ShoppingCart;
import com.robert.repository.ShoppingCartRepository;
import com.robert.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingRepository;
	
	@Override
	@Transactional
	public void addGame(String game, String user) {
		shoppingRepository.save(new ShoppingCart(user, game, 1));		
	}

	@Override
	@Transactional
	public void addOneMore(String game, String user) {
		ShoppingCart cart = shoppingRepository.getShoppingCart(game, user);
		cart.setCantidad(cart.getCantidad()+1);
		shoppingRepository.save(cart);
	}

	@Override
	@Transactional
	public void removeOne(String game, String user) {
		ShoppingCart cart = shoppingRepository.getShoppingCart(game, user);
		if(cart.getCantidad() > 1) {
			cart.setCantidad(cart.getCantidad()-1);
			shoppingRepository.save(cart);
		}else {
			removeGame(game, user);
		}
	}

	@Override
	@Transactional
	public void removeGame(String game, String user) {
		ShoppingCart cart = shoppingRepository.getShoppingCart(game, user);
		shoppingRepository.delete(cart);
	}

	@Override
	public List<ShoppingCart> getCarts() {
		return shoppingRepository.findAll();
	}

	@Override
	public List<ShoppingCart> getCartByUser(String user) {
		return shoppingRepository.allSCByUser(user);
	}
	
	@Override
	public ShoppingCart getCartByUserAndGame(String user, String game) {
		return shoppingRepository.getShoppingCart(game, user);
	}
	

}
