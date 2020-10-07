package com.inovareti.rpontobackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovareti.rpontobackend.domain.Funcionario;
import com.inovareti.rpontobackend.enums.Perfil;
import com.inovareti.rpontobackend.repositories.FuncionarioRepository;
import com.inovareti.rpontobackend.security.UserSS;
import com.inovareti.rpontobackend.services.exceptions.AuthorizationException;

@Service
public class FuncionarioService {

	
	@Autowired
	private FuncionarioRepository funcRepo;
	
	public Funcionario findByEmail(String email) {
		System.out.println("Pesquisando:"+email);
		UserSS user = UserService.authenticated();
		if(user !=null) {
			Funcionario func = funcRepo.findByEmail(email);
			if(!user.hasHole(Perfil.ADMIN)) {
				if(user.getUsername().equals(func.getEmail())) {
					return func;
				}else {
					throw new AuthorizationException("Acesso Negado");
				}
			}else {
				return func;
			}
		}else {
			throw new AuthorizationException("Acesso Negado");
			
		}
			
		
	}
	
	
}
