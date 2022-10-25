package com.gaurav.microservices.pokemonshop.model;


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
}
