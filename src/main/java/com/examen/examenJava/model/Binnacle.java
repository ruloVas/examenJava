package com.examen.examenJava.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Binnacle {

	private Long id;
    private String servicioApi;
    private String uri;
    private LocalDateTime consultationDate;
	
}
