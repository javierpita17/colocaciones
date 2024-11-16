package com.colocaciones.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colocaciones.model.Colocacion;
import com.colocaciones.repository.ColocacionRepository;
import com.colocaciones.service.IColocacionService;

@Service
public class ColocacionServiceImpl implements IColocacionService{
	
	@Autowired
	private ColocacionRepository colocacionesRepo;

	@Override
	public void saveAllColocaciones(List<Colocacion> colocaciones) {
		colocacionesRepo.saveAll(colocaciones);
	}

}
