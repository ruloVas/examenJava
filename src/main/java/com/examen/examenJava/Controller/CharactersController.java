package com.examen.examenJava.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.examen.examenJava.service.CharacterService;

import jakarta.websocket.server.PathParam;

@RestController
@ResponseBody
@RequestMapping("/examen")
public class CharactersController {

	private static final Log log = LogFactory.getLog(CharactersController.class);
	
	@Autowired
    private CharacterService characterService;
	
	@GetMapping("/charactersList")
    public List<Object> charactersList() {
		log.info("Consulta CharactersController - charactersList() ");
		Object[] result = characterService.getCharactersList();
        return Arrays.asList(result);
    }
	
	@GetMapping("/charactersList/{characterId}")
    public Object[] character(@PathParam("characterId") String characterId) {
		log.info("Consulta CharactersController - character() ");
		Object[] result = characterService.getCharacter(characterId);
        return result;
    }
	
}
