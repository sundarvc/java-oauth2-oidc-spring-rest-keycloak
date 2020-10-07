package com.sap.ibso.rest.demo.model.exceptions;


public class ProductNotFoundException extends RuntimeException {

 public ProductNotFoundException(Long id) {
    super("Could not find product " + id);
  }
}
