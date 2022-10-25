package com.gaurav.microservices.pokemonshop.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.sound.sampled.Port;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaurav.microservices.pokemonshop.model.CartItem;
import com.gaurav.microservices.pokemonshop.model.CartItemDTO;
import com.gaurav.microservices.pokemonshop.model.Product;
import com.gaurav.microservices.pokemonshop.repo.CartItemRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CartItemController {

	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	Map<Integer, Product> productMap;
	
	Integer defaultCartId = 1;
	
	public CartItemController() {
		
	}
	
	@GetMapping("/cart-items")
	public List<CartItemDTO> findAll(){
	List<CartItem> items = cartItemRepository.findByCartId(defaultCartId);
	
	initProductMap();
	
	List<CartItemDTO> result = new ArrayList<>();  
	items.forEach(ci -> result.add(new CartItemDTO(ci, productMap.get(ci.getProductId()))));
	
	return result;
}
	
	@GetMapping("/cart-items/{productId}")
	public ResponseEntity<?> add(@PathVariable int productId){
		if(productMap==null) {
			initProductMap();
		}
		Predicate<CartItem> filterByCartId = ci -> ci.getCartId()==defaultCartId;
		CartItem item = cartItemRepository.findByProductId(productId).stream().filter(filterByCartId).findFirst().orElse(null);
		if(item==null) {
			item = new CartItem();
			item.setAmount(1);
			item.setCartId(defaultCartId);
			item.setProductId(productId);
		}else {
			item.setAmount(item.getAmount()+1);
		}
		
		return ResponseEntity.ok(cartItemRepository.save(item));
		
	}
	
	@DeleteMapping("/cart-items")
	public ResponseEntity<?> deleteItems(){
		cartItemRepository.deleteAll();
		return ResponseEntity.ok().build();
	}
	
	private void initProductMap() {
		ObjectMapper mapper = new ObjectMapper();
		log.info("calling product service");
		Object object = restTemplate.getForEntity("http://PRODUCT-SERVICE/products", Object.class).getBody();
		log.info("retrieved product list");
		Product[] products = mapper.convertValue(object, Product[].class);
		productMap = Arrays.asList(products).stream().collect(Collectors.toMap(Product::getId,Function.identity()));
	}
}
