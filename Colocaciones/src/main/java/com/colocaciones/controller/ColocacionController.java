package com.colocaciones.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.colocaciones.model.Colocacion;
import com.colocaciones.service.IColocacionService;

@RestController
@RequestMapping("/colocacion")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ColocacionController {
	
	@Autowired
	private IColocacionService colocacionService;
	
	
	@Transactional
    @PostMapping("/carga")
    public ResponseEntity<Map<String, Object>> uploadFileColocaciones(@RequestParam("file") MultipartFile multipartFile) {
		Map<String,Object> response = new HashMap<>();
		try {
			colocacionService.cargaColocacionesBD(multipartFile);
		}catch (Exception e) {
			response.put("MENSAJE", "ERROR AL CARGAR EL ARCHIVO");
			e.printStackTrace();
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("MENSAJE", "ARCHIVO CARGADO EXITOSAMENTE");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@Transactional
    @GetMapping("/findAll")
    public List<Colocacion> finAll() {
		Map<String,Object> response = new HashMap<>();
		List<Colocacion> colocaciones = new ArrayList<>();
		try {
			colocaciones = colocacionService.getAllColocaciones();
		}catch (Exception e) {
			response.put("MENSAJE", "ERROR AL LISTAR COLOCACIONES");
			e.printStackTrace();
			//return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("MENSAJE", colocaciones);
		//return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		return colocaciones;
	}
	
	@GetMapping("/ping")
	 public ResponseEntity<Map<String, Object>> ping(){
		Map<String,Object> response = new HashMap<>();
		response.put("MENSAJE", "PONG");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}

}
