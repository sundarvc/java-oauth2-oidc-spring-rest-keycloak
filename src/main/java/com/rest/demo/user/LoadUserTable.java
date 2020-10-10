package com.rest.demo.user;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoadUserTable {
	
	public static final UUID u1 = UUID.fromString("35976d03-86ec-4543-bee9-25e52255bd4a");
	public static final UUID u2 = UUID.fromString("79377521-52a8-43aa-bcff-3cb9c30c33d0");
	public static final UUID u3 = UUID.fromString("c2abed16-6606-4894-800f-4cdb374b2d97");

	private static final Logger log = LoggerFactory.getLogger(LoadUserTable.class);


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	



	@Bean
	CommandLineRunner initUser(UserRepository repository) {

		
		return args -> {
			User test1 = new User(u1,"test1", passwordEncoder().encode("abc111"));
			test1.addAuthority("READ");
			User test2 = new User(u2,"test2", passwordEncoder().encode("abc222"));
			test2.addAuthority("UPDATE");
			test2.addAuthority("READ");
			User test3 = new User(u3,"test3", passwordEncoder().encode("abc333"));
			test3.addAuthority("CREATE");
			log.info("Preloading " + repository.save(test1));
			log.info("Preloading " + repository.save(test2));
			log.info("Preloading " + repository.save(test3));
		};
	}

}
