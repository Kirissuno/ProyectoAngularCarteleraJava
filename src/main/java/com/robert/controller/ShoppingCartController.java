package com.robert.controller;

import java.util.List;
import java.util.Properties;

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
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
	
	@PostMapping("/cart/sendMail/{usuario}")
	public void sendMail(@RequestBody List<ShoppingCart> carts, @PathVariable String usuario) {
		
		final String username = "javarobertsmtp@gmail.com";
        final String password = "Asdfghjk1@";
        
        String allGames = "";
        
        for(ShoppingCart cart : carts) {
        	allGames += cart.getVideojuego() + " x" + cart.getCantidad() + " unidades | ";
        }
        
        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
		
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("javarobertsmtp@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("arfilip.1h@gmail.com")
            );
            message.setSubject("Compras");
            message.setText("El usuario "+ usuario + " ha hecho su pedido de los siguientes videojuegos " + allGames +" ,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}

}
