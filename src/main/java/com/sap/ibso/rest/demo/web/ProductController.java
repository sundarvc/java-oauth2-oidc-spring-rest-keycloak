package com.sap.ibso.rest.demo.web;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
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
	@PreAuthorize("hasAnyAuthority('SCOPE_product:read','SCOPE_product:write')")
	List<Product> all(@AuthenticationPrincipal Jwt jwt) {
		UUID owner = UUID.fromString(jwt.getClaim("user_id"));
		log.info("user_id = " + owner);
		return repository.findByOwner(owner);
	}

	@PostMapping("/products")
	@Transactional
	@PreAuthorize("hasAuthority('SCOPE_product:write')")
	Product newProduct(@RequestBody Product product, @AuthenticationPrincipal Jwt jwt) {
		UUID owner = UUID.fromString(jwt.getClaim("user_id"));
		log.info("Owner = " + owner);
		product.setOwner(owner);
		return repository.save(product);
	}

	// Single item

	@GetMapping("/products/{id}")
	@PostAuthorize("@owner.apply(returnObject,principal.claims['user_id'])")
	@PreAuthorize("hasAuthority('SCOPE_product:read')")
	Product one(@PathVariable("id") Long id) {

		return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
	}


	@DeleteMapping("/products/{id}")
	@PreAuthorize("hasAuthority('SCOPE_product:write')")
	void deleteProduct(@PathVariable("id") Long id, @AuthenticationPrincipal Jwt jwt) {
		UUID owner = UUID.fromString(jwt.getClaim("user_id"));
		log.info("Owner = " + owner);
		repository.deleteById(id,owner);
	}

	@PutMapping(path = "/products/{id}")
	@PostAuthorize("@owner.apply(returnObject, principal.claims['user_id'])")
	@Transactional
	@PreAuthorize("hasAuthority('SCOPE_product:write')")
	public Optional<Product> revise(@PathVariable("id") Long id, @RequestBody String text) {
		repository.revise(id, text);
		return repository.findById(id);
	}
}
