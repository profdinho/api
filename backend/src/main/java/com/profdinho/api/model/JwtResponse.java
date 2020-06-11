package com.profdinho.api.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -3300048933084747480L;

	private final String jwttoken;
	
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

}
