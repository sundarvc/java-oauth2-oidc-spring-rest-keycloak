package com.sap.ibso.rest.demo.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoadUserTable {

	private static final Logger log = LoggerFactory.getLogger(LoadUserTable.class);


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner initUser(UserRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new User("test1", passwordEncoder().encode("abc111"))));
			log.info("Preloading " + repository.save(new User("test2", passwordEncoder().encode("abc222"))));
		};
	}

}
