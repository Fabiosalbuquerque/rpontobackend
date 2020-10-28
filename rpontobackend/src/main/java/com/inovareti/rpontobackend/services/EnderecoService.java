package com.inovareti.rpontobackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovareti.rpontobackend.domain.Endereco;
import com.inovareti.rpontobackend.repositories.EnderecoRepository;
import com.inovareti.rpontobackend.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco find(String id) {
		Optional<Endereco> obj = enderecoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
	}
	
	public Endereco saveNewEndereco(Endereco obj) {
		obj.setId(null);
		return  enderecoRepository.save(obj);
	}
}
