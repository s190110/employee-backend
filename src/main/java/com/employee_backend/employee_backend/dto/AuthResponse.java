package com.employee_backend.employee_backend.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
   
    public String getToken() {
        return token;
    }
	public void setToken(String token) {
		this.token = token;
	}
}
