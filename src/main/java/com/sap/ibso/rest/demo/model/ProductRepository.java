package com.sap.ibso.rest.demo.model;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM products p WHERE LOWER(p.owner) = LOWER(:owner)")
	List<Product> findByOwner(@Param("owner") UUID owner);
	
}
