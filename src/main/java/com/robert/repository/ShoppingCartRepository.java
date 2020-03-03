package com.robert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robert.model.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {
	
	@Query("select sc from ShoppingCart sc where sc.videojuego = ?1 and usuario = ?2")
	public ShoppingCart getShoppingCart(String titulo, String usuario);
	
	@Query("select sc from ShoppingCart sc where usuario = ?1")
	public List<ShoppingCart> allSCByUser(String usuario);
	
}
