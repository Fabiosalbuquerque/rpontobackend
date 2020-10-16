package com.inovareti.rpontobackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovareti.rpontobackend.domain.Funcionario;
import com.inovareti.rpontobackend.domain.Registro;
import com.inovareti.rpontobackend.dto.RegistroDTO;
import com.inovareti.rpontobackend.dto.RegistroNewDTO;
import com.inovareti.rpontobackend.enums.Perfil;
import com.inovareti.rpontobackend.repositories.RegistroRepository;
import com.inovareti.rpontobackend.security.UserSS;
import com.inovareti.rpontobackend.services.exceptions.AuthorizationException;
import com.inovareti.rpontobackend.services.exceptions.ObjectNotFoundException;


@Service
public class RegistroService {

	
	@Autowired
	private RegistroRepository registroRepo;
	
	
	@Autowired
	private FuncionarioService funcionarioService;
	
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
	
	public RegistroDTO buscaUltimoregistro(String email) {
		Funcionario func = funcionarioService.findByEmail(email);
		
		if(func!=null) {
			
			return new RegistroDTO(registroRepo.findFirstByFuncionarioEmailOrderByInstanteDesc(func.getEmail()));
		}else {
			return null;
		}
	}
	
	public List<RegistroDTO> buscaUltimos10registros(String email) {
		Funcionario func = funcionarioService.findByEmail(email);
		List<RegistroDTO> ultimos = new ArrayList<>();
		List<Registro> ultimosregs =  new ArrayList<>();
		if(func!=null) {
			ultimosregs = registroRepo.findFirst5ByFuncionarioEmailOrderByInstanteDesc(func.getEmail());
			ultimos = ultimosregs.stream().map(x -> new RegistroDTO(x)).collect(Collectors.toList());
			return ultimos;
		}else {
			return null;
		}
	}
	
	public Registro fromDTO(RegistroNewDTO obj) {
		
		Funcionario func =  funcionarioService.findByEmail(obj.getFuncionarioEmail());
		
		if(func!=null) {
			Registro novoReg= new Registro(null,obj.getInstante(),obj.getTipoRegistro(),func);
			novoReg.setDateRegistroFromInstante();
			if(novoReg.getTipoRegistro()==null || novoReg.getTipoRegistro().equals("")) {
				throw new AuthorizationException("Tipo de Registro Inválido");
			}
			return novoReg;
		}else {
			throw new ObjectNotFoundException("Funcionário não encontrado! Email: " + obj.getFuncionarioEmail() + ", Tipo: " + Funcionario.class.getName());
		}
		
		
	}
	
}
