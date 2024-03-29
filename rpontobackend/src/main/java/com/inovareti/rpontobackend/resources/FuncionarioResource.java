package com.inovareti.rpontobackend.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.inovareti.rpontobackend.domain.Funcionario;
import com.inovareti.rpontobackend.dto.NovoFuncionarioDTO;
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
	
	@PostMapping(value="/picture")
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile multipartfile){
		
		URI uri = funcService.uploadProfilePicture(multipartfile);
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping(value="/novo")
	public ResponseEntity<Void> insertFuncionario(@Valid @RequestBody NovoFuncionarioDTO objDTO){
		
		Funcionario obj = funcService.novoFuncionarioFromDTO(objDTO);
		obj = funcService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping(value="/atualizaAprovador")
	public ResponseEntity<Void> atualizaFuncionarioAprovador(@RequestParam(name="email") String email){
		
		Funcionario obj = funcService.atualizaAprovador(email);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
}
