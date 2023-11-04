package com.examen.examenJava.service;

import java.util.List;

import com.examen.examenJava.model.Binnacle;

public interface IBinnacleService {

	public List<Binnacle> findAll();
	public Binnacle finById(Long id);
	public boolean save(Binnacle binnacle);
	
}
