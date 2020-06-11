package com.profdinho.api.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest implements Serializable {

	private static final long serialVersionUID = -6520066952514644696L;

	private String username;
	private String password;
	
	public JwtRequest() {}
	
	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

}
