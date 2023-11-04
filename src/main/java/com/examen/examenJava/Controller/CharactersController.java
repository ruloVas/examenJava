package com.examen.examenJava.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.examenJava.model.dto.CharacterReponseOutDto;
import com.examen.examenJava.model.dto.CharactersReponseOutDto;
import com.examen.examenJava.service.ICharacterService;

/**
 * CharactersController
 * Obtiene los datos de Personajes de Marvel
 * @author Raul Vasquez
 * @version 1.0
 *
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/examen")
public class CharactersController {

	private static final Log log = LogFactory.getLog(CharactersController.class);
	
	@Autowired
    private ICharacterService characterService;
	
	/**
     * Metodo obtiene la lista de Personajes de Marvel
     * @return 
     */
	@GetMapping("/charactersList")
    public List<CharactersReponseOutDto> charactersList() {
		log.info("Consulta CharactersController - charactersList() ");
		return characterService.getCharactersList();      
    }
	
	/**
     * Metodo que obtiene personaje por Id
     * @param String characterId
     * @return 
     */
	@GetMapping("/character/{characterId}")
    public CharacterReponseOutDto getCharacterById(@PathVariable("characterId") String characterId) {
		log.info("Consulta CharactersController - getCharacterById() ");
		return characterService.getCharacterById(characterId);
    }
	
}
