package com.examen.examenJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examen.examenJava.model.entity.BinnacleEntity;

public interface IBinnacleRepository extends JpaRepository<BinnacleEntity, Long>{

}
