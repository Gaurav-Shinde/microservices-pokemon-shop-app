package com.gaurav.microservices.pokemonshop.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gaurav.microservices.pokemonshop.model.CartItemDTO;
import com.gaurav.microservices.pokemonshop.model.Order;
import com.gaurav.microservices.pokemonshop.model.OrderItem;
import com.gaurav.microservices.pokemonshop.repo.OrderRepository;

@RestController
public class OrderController {

	@Autowired
	OrderRepository orderRepository;
	
	//add order
	@PostMapping("/orders")
	public Order addOrder(@RequestBody List<CartItemDTO> cartItems) {
		Order order = new Order();
		List<OrderItem> orderItems = order.getItems();
		Double total = cartItems.stream()
				.peek(ci -> orderItems.add(new OrderItem(ci.getProduct().getId(), ci.getAmount(), order)))
				.map(ci-> ci.getProduct().getPrice() * ci.getAmount())
				.reduce(Double::sum).orElse(null);
		order.setDate(LocalDateTime.now().toString());
		order.setTotal(total);
		order.setItems(orderItems);
		
		return orderRepository.save(order);
	}
	
	//get all
	@GetMapping("/orders")
	public List<Order> findAllOrders(){
		return orderRepository.findAll();
	}
}
