package com.robert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robert.dto.UserDTO;
import com.robert.dto.VideogameDTO;
import com.robert.model.ShoppingCart;
import com.robert.service.ShoppingCartService;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingService;
	
	@PostMapping("/cart")
	public void addGame(@RequestBody VideogameDTO game, @RequestBody UserDTO user ) {
		shoppingService.addGame(game, user);
	}
	
	@PostMapping("/cart/more")
	public void addOneMore(@RequestBody VideogameDTO game, @RequestBody UserDTO user ) {
		shoppingService.addOneMore(game, user);
	}
	
	@PostMapping("/cart/less")
	public void removeOne(@RequestBody VideogameDTO game, @RequestBody UserDTO user) {
		shoppingService.removeOne(game, user);
	}
	
	@DeleteMapping("/cart")
	public void removeGame(VideogameDTO game, UserDTO user) {
		shoppingService.removeGame(game, user);
	}
	
	@GetMapping("/carts")
	public List<ShoppingCart> getCarts() {
		return shoppingService.getCarts();
	}

	@GetMapping("/cart")
	public List<ShoppingCart> getCartByUser(@RequestBody UserDTO user) {
		return shoppingService.getCartByUser(user);
	}

}
