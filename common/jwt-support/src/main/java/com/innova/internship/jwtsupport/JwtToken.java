package com.innova.internship.jwtsupport;

import java.io.Serializable;

public class JwtToken implements Serializable {
	private String accessToken;
	private String refreshToken;


	private JwtToken() {
	}

	public JwtToken(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}


	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

}
