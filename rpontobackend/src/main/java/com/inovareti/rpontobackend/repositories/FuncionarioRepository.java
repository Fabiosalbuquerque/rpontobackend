package com.inovareti.rpontobackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inovareti.rpontobackend.domain.Funcionario;

@Repository
public interface FuncionarioRepository extends MongoRepository<Funcionario,String>{

	Funcionario findByEmail(String email);
}
