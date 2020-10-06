package com.sap.ibso.rest.demo.model.exceptions;


public class EmployeeNotFoundException extends RuntimeException {

 public EmployeeNotFoundException(Long id) {
    super("Could not find employee " + id);
  }
}
