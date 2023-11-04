package com.examen.examenJava.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.examenJava.model.Binnacle;
import com.examen.examenJava.service.IBinnacleService;

/**
 * BinnacleController
 * Guarda y obtiene informacion de la bitacora
 * @author Raul Vasquez
 * @version 1.0
 *
 */
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/examen")
public class BinnacleController {
	
	private static final Log log = LogFactory.getLog(BinnacleController.class);
	
	@Autowired
    private IBinnacleService binnalceService;
	
	/**
     * Metodo que ontiene los registros de la bitacora
     * @return 
     */
	@GetMapping("/logsList")
    public  List<Binnacle> findAll() {
		log.info("Consulta BinnacleController - findAll() ");
		return binnalceService.findAll();      
    }
	
	/**
     * Metodo que guarda registro en bitacora
     * @param String characterId
     * @return 
     */
	@GetMapping("/saveLog")
    public Binnacle save(@RequestBody Binnacle binaccle) {
		log.info("Consulta BinnacleController - getCharacterById() ");
		binnalceService.save(binaccle);
		return binaccle;
    }

}
