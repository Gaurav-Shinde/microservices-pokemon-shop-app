package com.gaurav.microservices.pokemonshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gaurav.microservices.pokemonshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
