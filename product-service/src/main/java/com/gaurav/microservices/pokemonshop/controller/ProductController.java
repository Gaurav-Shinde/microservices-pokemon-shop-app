package com.gaurav.microservices.pokemonshop.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaurav.microservices.pokemonshop.model.Product;
import com.gaurav.microservices.pokemonshop.repo.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	//add
	@PostMapping("/products")
	public Object add(@RequestBody Product product) throws JsonMappingException, JsonProcessingException {
		String apiUrl = "https://pokeapi.co/api/v2/pokemon/";
		ObjectMapper objectMapper = new ObjectMapper();
		
		if(product!=null && product.getImg()!=null) {
			String pokemonName = product.getImg();
			
			//rest template and get img url
			HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			log.info("calling pokemon api");
			String jsonString = restTemplate.exchange(apiUrl+pokemonName, HttpMethod.GET,entity,String.class).getBody();
			log.info("retrieved json data string");
			JsonNode jsonNode = objectMapper.readTree(jsonString);
			String imgUrl = jsonNode.get("sprites").get("other").get("dream_world").get("front_default").asText();
			product.setImg(imgUrl);
			return productRepository.save(product);
		}
		
		
		return null;
	}
	
	//get
	
	//get all
	@GetMapping("/products")
	public List<Product> findAll(){
//		return productRepository.findAll();
		log.info("getting all products"); 	
		return (productRepository.findAll());
	}
	
	//update
	
	//delete
}
