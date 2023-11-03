package com.examen.examenJava.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class ComicsResponseDto {

	private int available;
	private String collectionURI;
	private List<ItemsResponseDto> items;
	private  int  returned;
	
}
