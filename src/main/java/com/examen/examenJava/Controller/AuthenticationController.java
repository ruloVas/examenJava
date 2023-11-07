package com.examen.examenJava.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.examenJava.model.entity.RoleEntity;
import com.examen.examenJava.model.entity.UserEntity;
import com.examen.examenJava.model.enums.RoleNameEnum;
import com.examen.examenJava.model.request.LoginForm;
import com.examen.examenJava.model.request.SignUpForm;
import com.examen.examenJava.model.response.JwtResponse;
import com.examen.examenJava.model.response.ResponseMessage;
import com.examen.examenJava.repository.IUserRepository;
import com.examen.examenJava.security.jwt.JwtProvider;
import com.examen.examenJava.repository.IRoleRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * AuthenticationController
 * Authentica a los usuarios
 * @author Raul Vasquez
 * @version 1.0
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	  @Autowired
	  AuthenticationManager authenticationManager;
	  
	  @Autowired
	  IUserRepository userRepository;
	  
	  @Autowired
	  IRoleRepository roleRepository;
	  
	  @Autowired
	  PasswordEncoder encoder;
	  
	  @Autowired
	  JwtProvider jwtProvider;
	  
	  @PostMapping("/signin")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
		  Authentication authentication = authenticationManager.authenticate(
		       new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		  SecurityContextHolder.getContext().setAuthentication(authentication);
		  String jwt = jwtProvider.generateJwtToken(authentication);
		  UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		  return ResponseEntity.ok(new JwtResponse(jwt, "Bearer", userDetails.getUsername(), userDetails.getAuthorities()));
	  }
	  
	  @PostMapping("/registerUser")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	      return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
	          HttpStatus.BAD_REQUEST);
	    }
	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	      return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
	          HttpStatus.BAD_REQUEST);
	    }
	    // Creating user's account
	    UserEntity user = new UserEntity(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
	        encoder.encode(signUpRequest.getPassword()));
	    Set<String> strRoles = signUpRequest.getRole();
	    Set<RoleEntity> roles = new HashSet<>();
	    strRoles.forEach(role -> {
	      switch (role) {
	      case "admin":
	    	  RoleEntity adminRole = roleRepository.findByName(RoleNameEnum.ROLE_ADMIN)
	            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	        roles.add(adminRole);
	        break;
	      default:
	    	  RoleEntity userRole = roleRepository.findByName(RoleNameEnum.ROLE_USER)
	            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	        roles.add(userRole);
	      }
	    });
	    user.setRoles(roles);
	    userRepository.save(user);
	    return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	  }

}

