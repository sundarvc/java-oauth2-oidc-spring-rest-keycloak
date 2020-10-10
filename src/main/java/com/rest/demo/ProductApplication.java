package com.rest.demo;

import java.util.Optional;
import java.util.function.BiFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.rest.demo.model.Product;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ProductApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ProductApplication.class);


	@Bean
	BiFunction<Optional<Product>,String,Boolean> owner(){
		
		return (product,userId)-> product.filter(r->r.getOwner().toString().equals(userId)).isPresent();
	}
	


	public static void main(String... args) {
		SpringApplication.run(ProductApplication.class, args);
	}
}
