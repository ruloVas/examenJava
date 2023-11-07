package com.examen.examenJava.model.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
	
	private String token;
	private String type;
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	
}
