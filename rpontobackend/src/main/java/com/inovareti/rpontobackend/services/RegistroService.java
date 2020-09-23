package com.inovareti.rpontobackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovareti.rpontobackend.domain.Registro;
import com.inovareti.rpontobackend.repositories.RegistroRepository;
import com.inovareti.rpontobackend.services.exceptions.ObjectNotFoundException;

@Service
public class RegistroService {

	
	@Autowired
	private RegistroRepository registroRepo;
	
	public Registro find(String id) {
		Optional<Registro> reg = registroRepo.findById(id);
		
		return reg.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Registro.class.getName()));
	}
}
