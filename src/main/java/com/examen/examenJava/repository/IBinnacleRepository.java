package com.examen.examenJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.examenJava.model.entity.BinnacleEntity;

@Repository
public interface IBinnacleRepository extends JpaRepository<BinnacleEntity, Long>{

}
