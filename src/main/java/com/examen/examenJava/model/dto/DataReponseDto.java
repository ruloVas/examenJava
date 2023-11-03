package com.examen.examenJava.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class DataReponseDto {

	private int offset;
	private int limit;
	private int total;
	private int count;
	List<ResultsReponseDto> results;
	
}
