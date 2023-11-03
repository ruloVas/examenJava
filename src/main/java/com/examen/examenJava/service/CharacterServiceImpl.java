package com.examen.examenJava.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.examen.examenJava.model.dto.CharactersReponseDto;
import com.examen.examenJava.model.dto.CharactersReponseOutDto;
import com.examen.examenJava.model.dto.ResultsReponseDto;

@Service
public class CharacterServiceImpl implements CharacterService {
	
	private static final Log log = LogFactory.getLog(CharacterServiceImpl.class);

	@Override
	public List<CharactersReponseOutDto> getCharactersList() {
		List<CharactersReponseOutDto> character = new ArrayList<CharactersReponseOutDto>();
		log.info("Se obtiene el listado de los personajes");
		CharactersReponseDto response = this.getCharactersAPIList();
		if(response != null) {
			List<ResultsReponseDto> results = response.getData().getResults();
			for(ResultsReponseDto result : results) {
				CharactersReponseOutDto item = new CharactersReponseOutDto();
				item.setId(result.getId());
				item.setName(result.getName());
				item.setDescription(result.getDescription().isEmpty() ? "Personaje de Marvel" : result.getDescription());
				character.add(item);
			}
		}
		return character;
	}
	
	private CharactersReponseDto getCharactersAPIList() {
		CharactersReponseDto response = new CharactersReponseDto();
		try {
			log.info("Se consulta API Marvel");
			String uri = "https://gateway.marvel.com/v1/public/characters?"
							.concat("apikey=273ef25b253da9d9b3985e19e8ee6d0a&")
							.concat("ts=1&")
							.concat("hash=21a48d871694bb6b2ffe65bfec7a6b0e");
			HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<Object> entity = new HttpEntity<>("", headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<CharactersReponseDto> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
					entity, CharactersReponseDto.class);
			response = responseEntity.getBody();	
		} catch (Exception e) {
			log.error("CharacterService :: No se pudo consultar la API de Marvel", e);
			response = null;
		}
		return response;
	}

	@Override
	public Object[] getCharacter(String characterId) {
		log.info("Se consulta API Marvel");
		String uri = "https://gateway.marvel.com/v1/public/characters/{characterId}?"
						.concat("apikey=273ef25b253da9d9b3985e19e8ee6d0a&")
						.concat("ts=1&")
						.concat("hash=21a48d871694bb6b2ffe65bfec7a6b0e");
		uri.replace("{characterId}", characterId);
		RestTemplate restTemplate = new RestTemplate();
		Object[] response = restTemplate.getForObject(uri, Object[].class);
		return response;
	}

}
