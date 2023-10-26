package com.examen.examenJava.service;


import org.springframework.stereotype.Service;

@Service
public interface CharacterService {

	public Object[] getCharactersList();

	public Object[] getCharacter(String characterId);
	
}
