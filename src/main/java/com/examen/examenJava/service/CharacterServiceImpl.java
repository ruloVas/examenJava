package com.examen.examenJava.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.examen.examenJava.model.Binnacle;
import com.examen.examenJava.model.dto.CharacterReponseOutDto;
import com.examen.examenJava.model.dto.CharactersReponseDto;
import com.examen.examenJava.model.dto.CharactersReponseOutDto;
import com.examen.examenJava.model.dto.ResultsReponseDto;

@Service
public class CharacterServiceImpl implements ICharacterService {
	
	private static final Log log = LogFactory.getLog(CharacterServiceImpl.class);
	private static final  DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static final  String URL_API     = "https://gateway.marvel.com/v1/public/characters";
	private static final  String API_KEY     = "apikey=273ef25b253da9d9b3985e19e8ee6d0a";
	private static final  String TS          = "ts=1";
	private static final  String HASH        = "hash=21a48d871694bb6b2ffe65bfec7a6b0e";
	
	@Autowired
    private IBinnacleService binnalceService;

	@Override
	public List<CharactersReponseOutDto> getCharactersList() {
		List<CharactersReponseOutDto> character = new ArrayList<CharactersReponseOutDto>();
		log.info("Se obtiene el listado de los personajes");
		CharactersReponseDto response = this.getCharactersAPIList().getBody();
		if(response != null) {
			List<ResultsReponseDto> results = response.getData().getResults();
			for(ResultsReponseDto result : results) {
				CharactersReponseOutDto item = new CharactersReponseOutDto();
				item.setId(result.getId());
				item.setName(result.getName());
				item.setDescription(result.getDescription().isEmpty() ? "Personaje de Marvel" : result.getDescription());
				character.add(item);
			}
			binnalceService.save(new Binnacle(new Long(0),"getCharactersList",URL_API, 
					LocalDateTime.now().format(DATE_FORMATTER)));
		}
		return character;
	}
	
	@Override
	public ResponseEntity<CharactersReponseDto> getCharactersAPIList() {
		ResponseEntity<CharactersReponseDto> response = new ResponseEntity<CharactersReponseDto>(HttpStatus.OK);
		try {
			log.info("Se consulta API Marvel");
			String uri = String.format("%s?%s&%s&%s", URL_API,API_KEY,TS,HASH);
			HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<Object> entity = new HttpEntity<>("", headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<CharactersReponseDto> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
					entity, CharactersReponseDto.class);
			response = responseEntity;
			
		} catch (Exception e) {
			log.error("Error CharacterService - getCharactersAPIList ", e);
			response = null; 
		}
		return response;
	}

	@Override
	public CharacterReponseOutDto getCharacterById(String characterId) {
		log.info("Se obtiene el listado de los personajes");
		CharactersReponseDto response = this.getCharacter(characterId).getBody();
		CharacterReponseOutDto character = new CharacterReponseOutDto();
		if(response != null) {
			List<ResultsReponseDto> results = response.getData().getResults();
			for(ResultsReponseDto result : results) {
				character.setComicsParticipation(result.getComics().getAvailable());
				character.setComicsList(result.getComics().getItems());
				character.setStoriesList(result.getStories().getItems());
				character.setStoriesParticipation(result.getStories().getAvailable());
				character.setSeriesParticipation(result.getSeries().getAvailable());
				character.setSeriesList(result.getSeries().getItems());
				character.setEventsParticipation(result.getEvents().getAvailable());
				character.setEventsList(result.getEvents().getItems());
			}
			binnalceService.save(new Binnacle(new Long(0),"getCharacterById",String.format("%s/{%s}", URL_API,characterId), 
					LocalDateTime.now().format(DATE_FORMATTER)));
		}
		return character;
	}
	
	@Override
	public ResponseEntity<CharactersReponseDto> getCharacter(String characterId) {
		ResponseEntity<CharactersReponseDto> response = new ResponseEntity<CharactersReponseDto>(HttpStatus.OK);
		try {
			log.info("Se consulta API Marvel con Id: "+characterId);
			String uri = String.format("%s/%s?%s&%s&%s", URL_API,characterId,API_KEY,TS,HASH);
			HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<Object> entity = new HttpEntity<>("", headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<CharactersReponseDto> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
					entity, CharactersReponseDto.class);
			response = responseEntity;
		} catch (Exception e) {
			log.error("Error CharacterService - getCharacter ", e);
			response = null;
		}
		return response;
	}

}
