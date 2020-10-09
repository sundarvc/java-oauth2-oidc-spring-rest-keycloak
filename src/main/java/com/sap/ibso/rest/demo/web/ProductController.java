package com.sap.ibso.rest.demo.web;


import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sap.ibso.rest.demo.model.Product;
import com.sap.ibso.rest.demo.model.ProductRepository;
import com.sap.ibso.rest.demo.model.exceptions.ProductNotFoundException;

@RestController
class ProductController {

  private final ProductRepository repository;
  private static final Logger log = LoggerFactory.getLogger(ProductController.class);


  ProductController(ProductRepository repository) {
    this.repository = repository;
  }

  // Aggregate root

  @GetMapping("/products")
  List<Product> all(@AuthenticationPrincipal Jwt jwt) {
	UUID owner = UUID.fromString(jwt.getClaim("user_id"));
	log.info("user_id = " + owner);
    return repository.findByOwner(owner);
  }

  @PostMapping("/products")
  Product newProduct(@RequestBody Product product,@AuthenticationPrincipal Jwt jwt) {
	UUID owner = UUID.fromString(jwt.getClaim("user_id"));
	log.info("Owner = " + owner);
	product.setOwner(owner);
    return repository.save(product);
  }

  // Single item

  @GetMapping("/products/{id}")
  Product one(@PathVariable Long id) {

    return repository.findById(id)
      .orElseThrow(() -> new ProductNotFoundException(id));
  }

  @PutMapping("/products/{id}")
  Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {

    return repository.findById(id)
      .map(product -> {
    	  product.setName(newProduct.getName());
    	  product.setDescription(newProduct.getDescription());
        return repository.save(product);
      })
      .orElseGet(() -> {
    	  newProduct.setId(id);
        return repository.save(newProduct);
      });
  }

  @DeleteMapping("/products/{id}")
  void deleteProduct(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
