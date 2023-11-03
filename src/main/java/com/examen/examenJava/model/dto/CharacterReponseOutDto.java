package com.examen.examenJava.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class CharacterReponseOutDto {
	
	private int comicsParticipation;
	private List<ItemsResponseDto> comicsList;
	private int seriesParticipation;
	private List<ItemsResponseDto> seriesList;
	private int storiesParticipation;
	private List<ItemsSeriesResponseDto> storiesList;
	private int eventsParticipation;
	private List<ItemsResponseDto> eventsList;
	

}
