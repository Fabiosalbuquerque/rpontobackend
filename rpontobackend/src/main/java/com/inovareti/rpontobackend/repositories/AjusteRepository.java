package com.inovareti.rpontobackend.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inovareti.rpontobackend.domain.Ajuste;

@Repository
public interface AjusteRepository extends MongoRepository<Ajuste,String>{

	List<Ajuste> findByEmailAndStatusOrderByRequisicaoTimeStampDesc(String email,String status);
	
	List<Ajuste> findByApprovadoPorAndStatusOrderByRequisicaoTimeStampDesc(String approvadoPor,String status);
	
	
	 
}
