package com.inovareti.rpontobackend.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.inovareti.rpontobackend.domain.Registro;
import com.inovareti.rpontobackend.dto.RegistroDTO;
import com.inovareti.rpontobackend.dto.RegistroNewDTO;
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
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping()
	public ResponseEntity<List<RegistroDTO>> findAll() {
		
		List<Registro> list = regService.findAll();
		List<RegistroDTO> listdto = list.stream().map(obj -> new RegistroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listdto);
		
	}
	
	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody RegistroNewDTO objDTO){
		Registro obj = regService.fromDTO(objDTO);
		obj = regService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
