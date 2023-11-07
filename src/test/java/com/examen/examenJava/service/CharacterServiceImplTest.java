package com.examen.examenJava.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.examen.examenJava.model.dto.CharactersReponseDto;

class CharacterServiceImplTest {
	
	@Mock
	private ICharacterService service;

	@BeforeEach
	public void init() {	
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void getCharacters() {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		
		when(service.getCharactersAPIList()).thenReturn(new ResponseEntity<CharactersReponseDto>(HttpStatus.OK));
		
		ResponseEntity<CharactersReponseDto> response = service.getCharactersAPIList();
		
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		
	}
	
	@Test
	public void getCharactersById() {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		String id = "1011334";
		
		when(service.getCharacter(id)).thenReturn(new ResponseEntity<CharactersReponseDto>(HttpStatus.OK));
		
		ResponseEntity<CharactersReponseDto> response = service.getCharacter(id);
		
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		
	}
	
}
