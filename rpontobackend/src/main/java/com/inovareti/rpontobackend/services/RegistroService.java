package com.inovareti.rpontobackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovareti.rpontobackend.domain.Funcionario;
import com.inovareti.rpontobackend.domain.Registro;
import com.inovareti.rpontobackend.dto.RegistroDTO;
import com.inovareti.rpontobackend.dto.RegistroNewDTO;
import com.inovareti.rpontobackend.enums.Perfil;
import com.inovareti.rpontobackend.repositories.FuncionarioRepository;
import com.inovareti.rpontobackend.repositories.RegistroRepository;
import com.inovareti.rpontobackend.security.UserSS;
import com.inovareti.rpontobackend.services.exceptions.AuthorizationException;
import com.inovareti.rpontobackend.services.exceptions.ObjectNotFoundException;


@Service
public class RegistroService {

	
	@Autowired
	private RegistroRepository registroRepo;
	
	@Autowired
	private FuncionarioRepository funcionarioRepo;
	
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
	
	public Registro insert(Registro obj) {
		obj.setId(null);
		obj = registroRepo.save(obj);
		return obj;
	}
	
	public Registro fromDTO(RegistroDTO obj) {
		return new Registro(obj.getId(),obj.getInstante(),obj.getTipoRegistro(),null);
	}
	
	public Registro fromDTO(RegistroNewDTO obj) {
		Registro novoReg= new Registro();
		novoReg.setId(null);
		novoReg.setInstante(obj.getInstante());
		novoReg.setDateRegistroFromInstante();
		
		novoReg.setTipoRegistro(obj.getTipoRegistro());
		if(novoReg.getTipoRegistro()==null || novoReg.getTipoRegistro().equals("")) {
			throw new AuthorizationException("Tipo de Registro Inválido");
		}
		
		Funcionario func =  funcionarioRepo.findByEmail(obj.getEmail());
		if(func!=null) {
			novoReg.setFuncionario(func);
			//System.out.println(novoReg.toString());
			return novoReg;
		}else {
			throw new ObjectNotFoundException("Funcionário não encontrado! Email: " + obj.getEmail() + ", Tipo: " + Funcionario.class.getName());
		}
		
		
	}
	
}
