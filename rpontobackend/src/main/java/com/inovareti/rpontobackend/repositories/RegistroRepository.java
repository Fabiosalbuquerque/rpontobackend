package com.inovareti.rpontobackend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inovareti.rpontobackend.domain.Registro;

@Repository
public interface RegistroRepository extends MongoRepository<Registro,String>{

	
	 Registro findFirstByFuncionarioEmailOrderByInstanteDesc(String email);
	 
	 List<Registro> findFirst5ByFuncionarioEmailOrderByInstanteDesc(String email);
	 
	 
}
