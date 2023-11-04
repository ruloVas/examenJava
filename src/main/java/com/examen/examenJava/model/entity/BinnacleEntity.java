package com.examen.examenJava.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="binnacle")
public class BinnacleEntity implements Serializable {
	
	private static final long serialVersionUID = -5069994129861416357L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String servicioApi;
    private String uri;
    private LocalDateTime consultationDate;

}
