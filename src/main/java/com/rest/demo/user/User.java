
package com.rest.demo.user;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="users")
public class User {

	@Id
	private UUID id;

	@Column(nullable = false, unique = true)
	private String username;

	private Boolean enabled = true;
	
	private String password;
	
	@OneToMany(cascade=ALL, fetch=EAGER)
	List<UserAuthority> userAuthorities = new ArrayList<>();

	public User() {}

	public User(UUID id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public User(User user) {
		this.id = user.id;
		this.username = user.username;
		this.password = user.password;
		this.enabled = user.enabled;
		this.userAuthorities = new ArrayList<>(user.userAuthorities);
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public UUID getId() {
		return id;
	}


	public Boolean getEnabled() {
		return enabled;
	}



	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUserAuthorities(List<UserAuthority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}

	public void addAuthority(String authority) {
		this.userAuthorities.add(new UserAuthority(this, authority));
	}
	
	public List<UserAuthority> getUserAuthorities() {
		return userAuthorities;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + this.id + ", username='" + this.username + '\'' + ", password='" + this.password + '\''
				+ '}';
	}

}
