package com.examen.examenJava.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.examen.examenJava.model.dto.CharacterReponseOutDto;
import com.examen.examenJava.model.dto.CharactersReponseDto;
import com.examen.examenJava.model.dto.CharactersReponseOutDto;

public interface ICharacterService {

	public List<CharactersReponseOutDto> getCharactersList();

	public CharacterReponseOutDto getCharacterById(String characterId);

	public ResponseEntity<CharactersReponseDto> getCharactersAPIList();
	
	public ResponseEntity<CharactersReponseDto> getCharacter(String characterId);
	
}
