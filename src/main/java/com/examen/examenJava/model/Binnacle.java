package com.examen.examenJava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Binnacle {

	private Long id;
    private String servicioApi;
    private String uri;
    private String consultationDate;
	
}
