package com.inovareti.rpontobackend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inovareti.rpontobackend.domain.Empresa;
import com.inovareti.rpontobackend.services.EmpresaService;

@RestController
@RequestMapping(value="/empresas")
public class EmpresaResource {

	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping()
	public ResponseEntity<List<Empresa>> findall() {
		
		List<Empresa> obj = empresaService.findAll();
		return ResponseEntity.ok().body(obj);
		
	}
	
	
	
	
}
