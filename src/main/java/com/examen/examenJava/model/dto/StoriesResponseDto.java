package com.examen.examenJava.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class StoriesResponseDto {

	private int available;
	private String collectionURI;
	private List<ItemsSeriesResponseDto> items;
	private  int  returned;
	
}
