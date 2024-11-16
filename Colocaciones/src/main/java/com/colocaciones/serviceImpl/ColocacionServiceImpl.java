package com.colocaciones.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.colocaciones.model.Colocacion;
import com.colocaciones.repository.ColocacionRepository;
import com.colocaciones.service.IColocacionService;
import com.colocaciones.util.CargaExcelColocaciones;

@Service
public class ColocacionServiceImpl implements IColocacionService{
	
	@Autowired
	private CargaExcelColocaciones cargaExcelColocaciones;
	
	@Autowired
	private ColocacionRepository colocacionesRepo;

	@Override
	public void saveAllColocaciones(List<Colocacion> colocaciones) {
		colocacionesRepo.saveAll(colocaciones);
	}
	
	
	public void cargaColocacionesBD(MultipartFile file) throws IOException{
		InputStream in = null;
		try{
			in = file.getInputStream();
			List<Colocacion> colocaciones = new ArrayList<>();
			cargaExcelColocaciones.cargarArchivoColocaciones(in, colocaciones);
			this.saveAllColocaciones(colocaciones);
		}finally {
			if(in!=null) {
				in.close();
			}
		}
	}
	
	

}
