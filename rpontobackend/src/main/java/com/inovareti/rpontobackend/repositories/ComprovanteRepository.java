package com.inovareti.rpontobackend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inovareti.rpontobackend.domain.Comprovante;

@Repository
public interface ComprovanteRepository extends MongoRepository<Comprovante,String>{

	
	 List<Comprovante> findByDiaRegistroAndMesRegistroAndAnoRegistroOrderByInstanteRegistroDesc(Integer diaRegistro,Integer mesRegistro,Integer anoRegistro);
	 
}
