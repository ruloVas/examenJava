package com.examen.examenJava.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CharacterServiceImpl implements CharacterService {
	
	private static final Log log = LogFactory.getLog(CharacterServiceImpl.class);

	@Override
	public Object[] getCharactersList() {
		log.info("Se consulta API Marvel");
		String uri = "https://gateway.marvel.com/v1/public/characters?"
						.concat("apikey=273ef25b253da9d9b3985e19e8ee6d0a&")
						.concat("ts=1&")
						.concat("hash=21a48d871694bb6b2ffe65bfec7a6b0e");
		RestTemplate restTemplate = new RestTemplate();
		Object[] response = restTemplate.getForObject(uri, Object[].class);
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
