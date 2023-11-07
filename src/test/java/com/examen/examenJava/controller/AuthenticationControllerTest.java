package com.examen.examenJava.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.examen.examenJava.model.request.LoginForm;
import com.examen.examenJava.model.response.JwtResponse;
import com.examen.examenJava.security.jwt.JwtProvider;

class AuthenticationControllerTest {
	
	@InjectMocks
	AuthenticationController authenticationController;
	
	@MockBean
	private AuthenticationManager authenticationManager;
		
	@MockBean
	private JwtProvider jwtProvider;

	private static UserDetails userDet;
	private static String jwtToken;
	
	@BeforeEach
	public void init() {	
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void authenticateUserTest() {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		LoginForm loginRequest =  new LoginForm("raul","examnJava123");
		
		Authentication authentication =  mock(Authentication.class);
	    authentication.setAuthenticated(true);
	    when(authentication.isAuthenticated()).thenReturn(true);
	    
	    when(authenticationManager.authenticate(any())).thenReturn(authentication);
	    
	    when(jwtProvider.generateJwtToken(authentication)).thenReturn("124");
	    
	    userDet = (UserDetails) authentication.getPrincipal();
	    
	    new JwtResponse(jwtToken, "Bearer", userDet.getUsername(), userDet.getAuthorities());

		ResponseEntity<?> response = authenticationController.authenticateUser(loginRequest);
		
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		
	}	
		
		
	
}
