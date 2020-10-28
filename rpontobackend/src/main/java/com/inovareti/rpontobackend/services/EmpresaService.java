package com.inovareti.rpontobackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovareti.rpontobackend.domain.Empresa;
import com.inovareti.rpontobackend.repositories.EmpresaRepository;
import com.inovareti.rpontobackend.services.exceptions.ObjectNotFoundException;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepo;

	public List<Empresa> findAll() {

		return empresaRepo.findAll();
	}

	public Empresa findById(String id) {

		Optional<Empresa> obj = empresaRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Empresa.class.getName()));
	}
}
