package com.rest.demo.user;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="authorities")
public class UserAuthority {
	@Id
	UUID id;

	@ManyToOne
	User user;

	@Column
	String authority;

	UserAuthority() {}

	public UserAuthority(User user, String authority) {
		this.id = UUID.randomUUID();
		this.user = user;
		this.authority = authority;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String toString() {
		return "UserAuthority{" + "id=" + this.id + ", username='" + this.user.getUsername() + '\'' + ", authority='" + this.getAuthority() + '\''
				+ '}';
	}
}
