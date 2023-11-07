package com.examen.examenJava.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
	
	@NotBlank
	@Size(min=3, max=50)
	private String username;
	
	@NotBlank
	@Size(min=3, max=50)
	private String password;
	
}
