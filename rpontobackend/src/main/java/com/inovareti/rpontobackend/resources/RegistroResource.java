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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.inovareti.rpontobackend.domain.Registro;
import com.inovareti.rpontobackend.dto.HoraServidorDTO;
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
	
	@GetMapping(value="/last/{email}")
	public ResponseEntity<RegistroDTO> findLastRegisterByUser(@PathVariable String email ) {
		
		RegistroDTO obj = regService.buscaUltimoregistro(email);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping(value="/last4/{email}")
	public ResponseEntity<List<RegistroDTO>> findLast4RegisterByUser(@PathVariable String email ) {
		
		List<RegistroDTO> obj = regService.buscaUltimos4registros(email);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping(value="/data")
	public ResponseEntity<List<RegistroDTO>> findRegByUserByDt(@RequestParam(name="dia") String dia,@RequestParam(name="mes") String mes,@RequestParam(name="ano") String ano,@RequestParam(name="email") String email ) {
		
		List<RegistroDTO> obj = regService.buscaRegistrosData(dia,mes,ano,email);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping(value="/espelho")
	public ResponseEntity<List<RegistroDTO>> findRegByUserByMes(@RequestParam(name="mes") String mes,@RequestParam(name="ano") String ano,@RequestParam(name="email") String email ) {
		
		List<RegistroDTO> obj = regService.buscaRegistrosData(mes,ano,email);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping(value="/instanteServidor")
	public ResponseEntity<HoraServidorDTO> findInstanteServidor() {
		
		HoraServidorDTO atual = new HoraServidorDTO();
		return ResponseEntity.ok().body(atual);
		
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
		obj = regService.verificaConsistenciaRegistro	(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
