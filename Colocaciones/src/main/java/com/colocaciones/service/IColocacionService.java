package com.colocaciones.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.colocaciones.model.Colocacion;

public interface IColocacionService {
	
	public void saveAllColocaciones(List<Colocacion> colocaciones);
	
	public void cargaColocacionesBD(MultipartFile file) throws IOException;
	
	public List<Colocacion> getAllColocaciones();

}
