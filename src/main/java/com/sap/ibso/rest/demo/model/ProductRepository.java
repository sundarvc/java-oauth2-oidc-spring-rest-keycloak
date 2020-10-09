package com.sap.ibso.rest.demo.model;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM products p WHERE LOWER(p.owner) = LOWER(:owner)")
	List<Product> findByOwner(@Param("owner") UUID owner);
	
	@Modifying
	@Query("UPDATE products SET description = :text WHERE id = :id")
	void revise(Long id, String text);
	
	@Modifying
	@Transactional
	@Query("DELETE from products where id=:id and owner=:uuid")
	void deleteById(Long id, UUID uuid);
	
}
