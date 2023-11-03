package com.examen.examenJava.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResultsReponseDto {

	int id;
	String name;
	String description;
	String modified;
	ThumbnailResponseDto thumbnail;
	String resourceURI;
	ComicsResponseDto comics;
	SeriesResponseDto series;
	StoriesResponseDto stories;
	SeriesResponseDto events;
	List<UrlsResponseDto> urls;
	
}
