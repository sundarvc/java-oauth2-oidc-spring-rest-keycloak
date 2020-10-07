package com.sap.ibso.rest.demo.user;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

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
			User test1 = new User("test1", passwordEncoder().encode("abc111"));
			test1.addAuthority("READ");
			User test2 = new User("test2", passwordEncoder().encode("abc222"));
			test2.addAuthority("WRITE");
			test2.addAuthority("READ");
			log.info("Preloading " + repository.save(test1));
			log.info("Preloading " + repository.save(test2));
		};
	}

}
