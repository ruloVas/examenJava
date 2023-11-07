package com.examen.examenJava.model.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignUpForm {

	@NotBlank
	@Size(min=3, max=50)
	private String name;
	
	@NotBlank
	@Size(min=3, max=50)
	private String username;
	
	@NotBlank
	@Size(min=3, max=50)
	@Email
	private String email;
	
	private Set<String> role;
	
	@NotBlank
	@Size(min=3, max=50)
	@Email
	private String password;
 	
}
