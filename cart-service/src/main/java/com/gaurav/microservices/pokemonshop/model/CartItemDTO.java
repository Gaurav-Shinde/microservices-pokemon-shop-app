package com.gaurav.microservices.pokemonshop.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

	private int id;
	private Product product;
	private int cartId;
	private int amount;
	
	public CartItemDTO(CartItem cartItem, Product product){
		id = cartItem.getId();
		cartId = cartItem.getCartId();
		amount = cartItem.getAmount();
		this.product = product;
	}
}
