package com.sap.ibso.rest.demo;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class ProductApplication {

	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource) {
			@Override
			protected List<GrantedAuthority> loadUserAuthorities(String username) {
				return AuthorityUtils.createAuthorityList("ROLE_USER");
			}
		};
	}
	
	


	public static void main(String... args) {
		SpringApplication.run(ProductApplication.class, args);
	}
}
