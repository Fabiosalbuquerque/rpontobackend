package com.inovareti.rpontobackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inovareti.rpontobackend.domain.Endereco;

@Repository
public interface EnderecoRepository extends MongoRepository<Endereco,String>{

}
