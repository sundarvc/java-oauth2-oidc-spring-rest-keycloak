package com.sap.ibso.rest.demo.model;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="products")
public class Product {

	private @Id @GeneratedValue Long id;
	private String name;
	private String description;
	private UUID owner;

	Product() {
	}

	Product(UUID o,String name, String description) {

		this.name = name;
		this.description = description;
		this.owner = o;
	}
	
	Product(String name, String description) {

		this.name = name;
		this.description = description;
	}
	
	
	public UUID getOwner() {
		return owner;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Product))
			return false;
		Product product = (Product) o;
		return Objects.equals(this.id, product.id) && Objects.equals(this.name, product.name)
				&& Objects.equals(this.description, product.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.description);
	}

	@Override
	public String toString() {
		return "Product{" + "id=" + this.id + ", name='" + this.name + '\'' + ", description='" + this.description + '\'' + ", ownner='" + this.owner + '}';
	}
}