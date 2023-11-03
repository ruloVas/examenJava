package com.examen.examenJava.service;

import java.util.List;

import com.examen.examenJava.model.dto.CharacterReponseOutDto;
import com.examen.examenJava.model.dto.CharactersReponseOutDto;

public interface CharacterService {

	public List<CharactersReponseOutDto> getCharactersList();

	public CharacterReponseOutDto getCharacterById(String characterId);
	
}
