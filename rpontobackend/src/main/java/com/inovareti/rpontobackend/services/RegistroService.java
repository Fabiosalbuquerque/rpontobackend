package com.inovareti.rpontobackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovareti.rpontobackend.domain.Registro;
import com.inovareti.rpontobackend.enums.Perfil;
import com.inovareti.rpontobackend.repositories.RegistroRepository;
import com.inovareti.rpontobackend.security.UserSS;
import com.inovareti.rpontobackend.services.exceptions.AuthorizationException;
import com.inovareti.rpontobackend.services.exceptions.ObjectNotFoundException;


@Service
public class RegistroService {

	
	@Autowired
	private RegistroRepository registroRepo;
	
	public Registro find(String id) {
		
		UserSS user = UserService.authenticated();
		
		if(user !=null) {
			Optional<Registro> reg = registroRepo.findById(id);
			if(!user.hasHole(Perfil.ADMIN)) {
				Registro auxiliar = reg.orElse(null);
				if(user.getUsername().equals(auxiliar.getFuncionario().getEmail())) {
					return reg.orElseThrow(() -> new ObjectNotFoundException(
							"Objeto não encontrado! Id: " + id + ", Tipo: " + Registro.class.getName()));
				}else {
					throw new AuthorizationException("Acesso Negado");
				}
			}else {
				return reg.orElseThrow(() -> new ObjectNotFoundException(
						"Objeto não encontrado! Id: " + id + ", Tipo: " + Registro.class.getName()));
			}
		}else {
			throw new AuthorizationException("Acesso Negado");
			
		}
			
		
	}
	
	public List<Registro> findAll() {
		
		return registroRepo.findAll();
	}
	
}
