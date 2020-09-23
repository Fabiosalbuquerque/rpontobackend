package com.inovareti.rpontobackend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inovareti.rpontobackend.domain.Registro;
import com.inovareti.rpontobackend.services.RegistroService;

@RestController
@RequestMapping(value="/registros")
public class RegistroResource {

	@Autowired
	private RegistroService regService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Registro> find(@PathVariable String id ) {
		
		Registro obj = regService.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
}
