package com.inovareti.rpontobackend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inovareti.rpontobackend.domain.Funcionario;
import com.inovareti.rpontobackend.services.FuncionarioService;

@RestController
@RequestMapping(value="/funcionario")
public class FuncionarioResource {

	@Autowired
	private FuncionarioService funcService;
	
	@GetMapping(value="/{email}")
	public ResponseEntity<Funcionario> find(@PathVariable String email ) {
		
		Funcionario obj = funcService.findByEmail(email);
		return ResponseEntity.ok().body(obj);
		
	}
	
	
}
