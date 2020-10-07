package com.sap.ibso.rest.demo.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import com.sap.ibso.rest.demo.user.User;
import com.sap.ibso.rest.demo.user.UserAuthority;
import com.sap.ibso.rest.demo.user.UserRepository;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests(a -> {
			a.mvcMatchers(HttpMethod.GET, "/products/**").hasAuthority("READ").
			mvcMatchers(HttpMethod.PUT, "/products/**").hasAuthority("UPDATE").
			mvcMatchers(HttpMethod.POST, "/products/**").hasAuthority("CREATE").
			mvcMatchers(HttpMethod.DELETE, "/products/**").hasAuthority("DELETE").
			anyRequest().authenticated();
			
			
		})
		.httpBasic();
		http.csrf().disable();

	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource) {
			@Override
				protected List<GrantedAuthority> loadUserAuthorities(String username) {
				User user = userRepository.findByName(username);
				log.info(user.toString());
				List<GrantedAuthority> grantedAuthorities = new ArrayList<>(); 
				for (UserAuthority auth : user.getUserAuthorities()) {
				    grantedAuthorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
				    log.info(auth.toString());
				}
				return grantedAuthorities;
			}
		};
	}
	
 


}
