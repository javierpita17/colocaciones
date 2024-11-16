package com.colocaciones.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.colocaciones.service.IColocacionService;

@RestController
@RequestMapping("/colocacion")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ColocacionController {
	
	@Autowired
	private IColocacionService colocacionService;
	
	/*
	@Transactional
    @PostMapping("/carga")
    public ResponseEntity<Map<String, Object>> uploadFileColocaciones(@RequestParam("file") MultipartFile multipartFile) {
		
		
		try {
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	*/

}
