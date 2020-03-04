package com.robert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robert.model.ShoppingCart;
import com.robert.service.ShoppingCartService;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingService;
	
	@PostMapping("/cart/{user}")
	public void addGame(@RequestBody String game, @PathVariable String user ) {
		shoppingService.addGame(game, user);
	}
	
	@PostMapping("/cart/more/{game}/{user}")
	public void addOneMore(@PathVariable String game, @PathVariable String user ) {
		shoppingService.addOneMore(game, user);
	}
	
	@PostMapping("/cart/less/{game}/{user}")
	public void removeOne(@PathVariable String game, @PathVariable String user) {
		shoppingService.removeOne(game, user);
	}
	
	@DeleteMapping("/cart/{user}/{game}")
	public void removeGame(@PathVariable String user, @PathVariable String game) {
		shoppingService.removeGame(game, user);
	}
	
	@GetMapping("/carts")
	public List<ShoppingCart> getCarts() {
		return shoppingService.getCarts();
	}

	@GetMapping("/cart/{user}")
	public List<ShoppingCart> getCartByUser(@PathVariable String user) {
		return shoppingService.getCartByUser(user);
	}
	
	@GetMapping("/cart/{user}/{game}")
	public ShoppingCart getGameInCar(@PathVariable String user, @PathVariable String game) {
		return shoppingService.getCartByUserAndGame(user, game);
	}

}
