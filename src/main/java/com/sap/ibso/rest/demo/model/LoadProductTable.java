package com.sap.ibso.rest.demo.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sap.ibso.rest.demo.user.LoadUserTable;
import com.sap.ibso.rest.demo.user.User;
import com.sap.ibso.rest.demo.user.UserRepository;

@Configuration
class LoadProductTable {

  private static final Logger log = LoggerFactory.getLogger(LoadProductTable.class);

  @Bean
  CommandLineRunner initProduct(ProductRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Product(LoadUserTable.u1,"PC", "Windows 10 OS")));
      log.info("Preloading " + repository.save(new Product(LoadUserTable.u2,"Display", "Dell 27inch HDMI")));
      log.info("Preloading " + repository.save(new Product(LoadUserTable.u3,"Mobile Phone", "iPhone XR Retina Display")));
      
    };
  }
  

}
