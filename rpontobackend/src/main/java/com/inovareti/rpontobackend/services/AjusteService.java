package com.inovareti.rpontobackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovareti.rpontobackend.domain.Ajuste;
import com.inovareti.rpontobackend.domain.Registro;
import com.inovareti.rpontobackend.enums.Perfil;
import com.inovareti.rpontobackend.repositories.AjusteRepository;
import com.inovareti.rpontobackend.security.UserSS;
import com.inovareti.rpontobackend.services.exceptions.AuthorizationException;
import com.inovareti.rpontobackend.services.exceptions.ObjectNotFoundException;


@Service
public class AjusteService {

	
	@Autowired
	private AjusteRepository ajusteRepo;
	
	@Autowired
	private RegistroService registroService;
	@Autowired
	private FuncionarioService funcionarioService;
	
	
	public List<Ajuste> findAllbyEmailandStatus(String email, String status){
		return ajusteRepo.findByEmailAndStatusOrderByRequisicaoTimeStampDesc(email, status);
	}
	
	public List<Ajuste> findAllbyEmailAprovadorandStatus(String email, String status){
		return ajusteRepo.findByApprovadoPorAndStatusOrderByRequisicaoTimeStampDesc(email, status);
	}
	
	public Boolean AtualizaStatus(String[] id,String status) {
		Ajuste aux;
		Registro reg;
		for(int i=0;i<id.length;i++) {
			aux = this.find(id[i]);
			aux.setStatus(status);
			ajusteRepo.save(aux);
			reg = registroService.pesquisaRegistroEspecifico(aux.getDia(),aux.getMes(),aux.getAno(),aux.getTipoRegistro());
			if(reg!=null) {
			if(status.equals("APROVADO")) {
				reg.setAjustado("Y");
				reg.setRegAjustado(aux);
				reg.setInstante(aux.getInstanteRegistro());
				if(registroService.save(reg)) {
					System.out.println("Registro Atualizado");
				}
			}
			}else {
				if(status.equals("APROVADO")) {
					reg = new Registro();
					reg.setAjustado("Y");
					reg.setRegAjustado(aux);
					reg.setInstante(aux.getInstanteRegistro());
					reg.setFuncionario(funcionarioService.findByEmail(aux.getEmail()));
					reg.setEmpresa(funcionarioService.findByEmail(aux.getEmail()).getEmpresa());
					if(registroService.save(reg)) {
						System.out.println("Registro Atualizado");
					}
				}
			}
		}
		return true;
	}
	
	public Ajuste find(String id) {
		
		UserSS user = UserService.authenticated();
		
		if(user !=null) {
			Optional<Ajuste> reg = ajusteRepo.findById(id);
			if(!user.hasHole(Perfil.ADMIN)) {
				Ajuste auxiliar = reg.orElse(null);
				if(user.getUsername().equals(auxiliar.getEmail())) {
					return reg.orElseThrow(() -> new ObjectNotFoundException(
							"Objeto não encontrado! Id: " + id + ", Tipo: " + Ajuste.class.getName()));
				}else if(user.hasHole(Perfil.APROVADOR)){
					return reg.orElseThrow(() -> new ObjectNotFoundException(
							"Objeto não encontrado! Id: " + id + ", Tipo: " + Ajuste.class.getName()));
				}else {
					throw new AuthorizationException("Acesso Negadox");
				}
			}else {
				return reg.orElseThrow(() -> new ObjectNotFoundException(
						"Objeto não encontrado! Id: " + id + ", Tipo: " + Ajuste.class.getName()));
			}
		}else {
			throw new AuthorizationException("Acesso Negado");
			
		}
			
		
	}
	
	
	
	
	
	
	public Ajuste insert(Ajuste obj) {
		obj.setId(null);
		
		
		obj = ajusteRepo.save(obj);
		
		return obj;
	}
	
	


	
}
