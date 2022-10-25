package com.gaurav.microservices.pokemonshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem implements Serializable{

	@Id
	private int productId;
	
	private int amount;

	@Id
	@ManyToOne
	@JoinColumn(name =  "orderId")
	@JsonIgnore
	private Order order;
}
