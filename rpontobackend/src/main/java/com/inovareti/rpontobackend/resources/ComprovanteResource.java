package com.inovareti.rpontobackend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inovareti.rpontobackend.domain.Comprovante;
import com.inovareti.rpontobackend.services.ComprovanteService;

@RestController
@RequestMapping(value="/comprovantes")
public class ComprovanteResource {

	@Autowired
	private ComprovanteService comprovanteService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Comprovante> find(@PathVariable String id ) {
		
		Comprovante obj = comprovanteService.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	
	
	
	
	@GetMapping(value="/data")
	public ResponseEntity<List<Comprovante>> findComprovanteByUserByDt(@RequestParam(name="dia") String dia,@RequestParam(name="mes") String mes,@RequestParam(name="ano") String ano,@RequestParam(name="email") String email ) {
		
		List<Comprovante> obj = comprovanteService.buscaComprovantesData(dia,mes,ano,email);
		return ResponseEntity.ok().body(obj);
		
	}
	
	
}
