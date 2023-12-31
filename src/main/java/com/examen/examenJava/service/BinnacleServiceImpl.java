package com.examen.examenJava.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.examenJava.model.Binnacle;
import com.examen.examenJava.model.entity.BinnacleEntity;
import com.examen.examenJava.repository.IBinnacleRepository;

@Service
public class BinnacleServiceImpl implements IBinnacleService {

	private static final Log log = LogFactory.getLog(BinnacleServiceImpl.class);
	
	private static final  DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private IBinnacleRepository binnacleRepositoty;
	
	@Override
	public List<Binnacle> findAll() {
		List<Binnacle> binnacle = new ArrayList<Binnacle>();
		log.info("Se consultan todos los registros de la bitacora");
		List<BinnacleEntity> response = binnacleRepositoty.findAll();
		if(!response.isEmpty()) {
			binnacle = this.convertBinnacle(response);
		} else {
			log.info("No hay registros a mostrar");
		}
		return binnacle;
	}

	@Override
	public Binnacle finById(Long id) {
		Binnacle binnacle = new Binnacle();
		log.info("Se consultan resgistros de la bitacora por Id");
		Optional<BinnacleEntity> response = binnacleRepositoty.findById(id);
		if(response.isPresent()) {
			binnacle.setId(response.get().getId());
			binnacle.setServicioApi(response.get().getServicioApi());
			binnacle.setUri(response.get().getUri());
			binnacle.setConsultationDate(response.get().getConsultationDate().format(DATE_FORMATTER));
		} else {
			log.info("No hay registros a mostrar");
		}
		return binnacle;
	}

	@Override
	public boolean save(Binnacle binnacle) {
		boolean flag = false;
		log.info("Se intenta guardar resgistro en la bitacora");
		BinnacleEntity response = binnacleRepositoty.save(this.convertToEntity(binnacle));
		if (response != null) {
			flag = true;
			log.info("Se guardo resgistro en la bitacora con Id: "+response.getId());
		}
		return flag;
	}
	
	private List<Binnacle> convertBinnacle(List<BinnacleEntity> response) {
		List<Binnacle> binnacle = new ArrayList<Binnacle>();
		for(BinnacleEntity item : response) {
			Binnacle out = new Binnacle();
			out.setId(item.getId());
			out.setServicioApi(item.getServicioApi());
			out.setUri(item.getUri());
			out.setConsultationDate(item.getConsultationDate().format(DATE_FORMATTER));
			binnacle.add(out);
		}
		return binnacle;
	}
	
	private BinnacleEntity convertToEntity(Binnacle binnacle) {
		BinnacleEntity out = new BinnacleEntity();
		out.setServicioApi(binnacle.getServicioApi());
		out.setUri(binnacle.getUri());
		LocalDateTime localDateTime = LocalDateTime.parse(binnacle.getConsultationDate(), DATE_FORMATTER);
		out.setConsultationDate(localDateTime);
		return out;
	}

}
