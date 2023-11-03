package com.examen.examenJava.model.dto;

import lombok.Data;

@Data
public class CharactersReponseDto {
	
	private int code;
	private String status;
	private String copyright;
	private String attributionText;
	private String attributionHTML;
	private String etag;
	private DataReponseDto data;

}
