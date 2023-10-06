package com.AuthenticationServer.jwt;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthenticationRequest {
	
	@NotNull
	@NotEmpty
	@Size(min = 2, max = 50,message = "Username should be 2 to 50 characters size only.")
	private String username;
	@NotNull
	@NotEmpty
	@Size(min = 2, max = 50,message = "Password should be 2 to 50 characters size only.")
	private String password;
	 

	public AuthenticationRequest() {
		super();
	}

	public AuthenticationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

	@Override
	public String toString() {
		return "AuthenticationRequest [username=" + username + ", password=" + password + "]";
	}
	

}
