package com.inovareti.rpontobackend.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
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
	private ComprovanteService comprovanteService;
	
	
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
	
	public Registro pesquisaRegistroEspecifico(Integer dia, Integer mes, Integer ano,String tipo) {
		return registroRepo.findByDiaRegistroAndMesRegistroAndAnoRegistroAndTipoRegistroOrderByInstanteDesc(dia,mes,ano,tipo);
	}
	
	public List<Registro> findAll() {
		
		return registroRepo.findAll();
	}
	
	public boolean save(Registro r) {
		 r=registroRepo.save(r);
		 if(r.getId()!=null) {
			 
			comprovanteService.novoComprovanteFromRegistro(r);
				
			return true;
		}else {
			return false;
		}
	}
	
	public Registro verificaConsistenciaRegistro(Registro obj){
		
		Registro ultimo = registroRepo.findFirstByFuncionarioEmailOrderByInstanteDesc(obj.getFuncionario().getEmail());
		
		if(TimeUnit.MILLISECONDS.toMinutes(Calendar.getInstance().getTimeInMillis() - obj.getInstante()) < 2 ) {
			if(ultimo==null) {
				obj.setTipoRegistro("INICIO_JORNADA");
				return insert(obj);
			}else if(ultimo.getInstanteServidor()!=null) {
				if(obj.getTipoRegistro().equals("definidoPeloServidor")){
					if(ultimo.getTipoRegistro().equals("SAIDA_JORNADA")) {
						obj.setTipoRegistro("INICIO_JORNADA");
					}else if(ultimo.getTipoRegistro().equals("INICIO_JORNADA")) {
						obj.setTipoRegistro("INICIO_INTERVALO");
					}else if(ultimo.getTipoRegistro().equals("INICIO_INTERVALO")) {
						obj.setTipoRegistro("FIM_INTERVALO");
					}else if(ultimo.getTipoRegistro().equals("FIM_INTERVALO")) {
						obj.setTipoRegistro("SAIDA_JORNADA");
					}else {
						obj.setTipoRegistro("INICIO_JORNADA");
					}
					return insert(obj);
				}else {
					return insert(obj);
				}
			}else {
				obj.setTipoRegistro("INICIO_JORNADA");
				return insert(obj);
			}
			
		}else {
			return null;
		}
		
		
		
	}
	
	public Registro insert(Registro obj) {
		obj.setId(null);
		
		
		obj = registroRepo.save(obj);
		
		if(obj.getId()!=null) {
			comprovanteService.novoComprovanteFromRegistro(obj);
		}
		return obj;
	}
	
	public Registro fromDTO(RegistroDTO obj) {
		return new Registro(obj.getId(),obj.getInstante(),obj.getDiaRegistro(),obj.getMesRegistro(),obj.getAnoRegistro(),obj.getTipoRegistro(),null);
	}
	
	public RegistroDTO buscaUltimoregistro(String email) {
		Funcionario func = funcionarioService.findByEmail(email);
		
		if(func!=null) {
			if(registroRepo.findFirstByFuncionarioEmailOrderByInstanteDesc(func.getEmail())!=null) {
				return new RegistroDTO(registroRepo.findFirstByFuncionarioEmailOrderByInstanteDesc(func.getEmail()));
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	public List<RegistroDTO> buscaUltimos4registros(String email) {
		Funcionario func = funcionarioService.findByEmail(email);
		List<RegistroDTO> ultimos = new ArrayList<>();
		List<Registro> ultimosregs =  new ArrayList<>();
		if(func!=null) {
			ultimosregs = registroRepo.findFirst4ByFuncionarioEmailOrderByInstanteDesc(func.getEmail());
			ultimos = ultimosregs.stream().map(x -> new RegistroDTO(x)).collect(Collectors.toList());
			return ultimos;
		}else {
			return null;
		}
	}
	
	
	public List<RegistroDTO> buscaRegistrosData(String dia,String mes,String ano,String email) {
		Funcionario func = funcionarioService.findByEmail(email);
		List<RegistroDTO> ultimos = new ArrayList<>();
		List<Registro> ultimosregs =  new ArrayList<>();
		if(func!=null) {
			ultimosregs = registroRepo.findByDiaRegistroAndMesRegistroAndAnoRegistroOrderByInstanteDesc(Integer.parseInt(dia),Integer.parseInt(mes),Integer.parseInt(ano));
			ultimos = ultimosregs.stream().map(x -> new RegistroDTO(x)).collect(Collectors.toList());
			return ultimos;
		}else {
			return null;
		}
	}
	
	public List<RegistroDTO> buscaRegistrosData(String mes,String ano,String email) {
		Funcionario func = funcionarioService.findByEmail(email);
		List<RegistroDTO> ultimos = new ArrayList<>();
		List<Registro> ultimosregs =  new ArrayList<>();
		if(func!=null) {
			ultimosregs = registroRepo.findByMesRegistroAndAnoRegistroOrderByInstanteDesc(Integer.parseInt(mes),Integer.parseInt(ano));
			ultimos = ultimosregs.stream().map(x -> new RegistroDTO(x)).collect(Collectors.toList());
			return ultimos;
		}else {
			return null;
		}
	}
	
	public Registro fromDTO(RegistroNewDTO obj) {
		
		Funcionario func =  funcionarioService.findByEmail(obj.getFuncionarioEmail());
		
		if(func!=null) {
			Registro novoReg= new Registro(null,obj.getInstante(),0,0,0,obj.getTipoRegistro(),func);
			novoReg.setDateRegistroFromInstante();
			if(novoReg.getTipoRegistro()==null || novoReg.getTipoRegistro().equals("")) {
				throw new AuthorizationException("Tipo de Registro Inválido");
			}
			novoReg.setInstanteServidor(Calendar.getInstance().getTimeInMillis());
			return novoReg;
		}else {
			throw new ObjectNotFoundException("Funcionário não encontrado! Email: " + obj.getFuncionarioEmail() + ", Tipo: " + Funcionario.class.getName());
		}
		
		
	}
	
}
