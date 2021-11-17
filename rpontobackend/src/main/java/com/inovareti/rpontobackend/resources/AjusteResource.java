package com.inovareti.rpontobackend.resources;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.inovareti.rpontobackend.domain.Ajuste;
import com.inovareti.rpontobackend.dto.AjusteParaAprovarDTO;
import com.inovareti.rpontobackend.services.AjusteService;

@RestController
@RequestMapping(value="/ajustes")
public class AjusteResource {

	@Autowired
	private AjusteService ajusteService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Ajuste> find(@PathVariable String id ) {
		
		Ajuste obj = ajusteService.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping(value="/abertos")
	public ResponseEntity<List<Ajuste>> findAllOpen(@RequestParam(name="email") String email ) {
		
		
		return ResponseEntity.ok().body(ajusteService.findAllbyEmailandStatus(email, "EM_APROVACAO"));
		
	}
	
	@GetMapping(value="/aprovacoes")
	public ResponseEntity<List<Ajuste>> findAllToBeApproved(@RequestParam(name="email") String email ) {
		
		
		return ResponseEntity.ok().body(ajusteService.findAllbyEmailAprovadorandStatus(email, "EM_APROVACAO"));
		
	}
	
	
	
	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody Ajuste objDTO){
		
		objDTO = ajusteService.insert(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objDTO.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping(value="/aprovacoes/update")
	public ResponseEntity<String> Atualiza(@Valid @RequestBody AjusteParaAprovarDTO objDTO){
		System.out.println("id:"+objDTO.getId()[0]);
		System.out.println("status:"+objDTO.getStatus());
		if(ajusteService.AtualizaStatus(objDTO.getId(), objDTO.getStatus())) {
			return ResponseEntity.ok().body("Atualizado");
		}else {
			return ResponseEntity.ok().body("Falha");
		}
		
		
	}
}
