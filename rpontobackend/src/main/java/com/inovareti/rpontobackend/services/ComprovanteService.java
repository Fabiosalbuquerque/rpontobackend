package com.inovareti.rpontobackend.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovareti.rpontobackend.domain.Comprovante;
import com.inovareti.rpontobackend.domain.Registro;
import com.inovareti.rpontobackend.repositories.ComprovanteRepository;
import com.inovareti.rpontobackend.services.exceptions.ObjectNotFoundException;

@Service
public class ComprovanteService {

	@Autowired
	private ComprovanteRepository comprovanteRepo;

	public List<Comprovante> findAll() {

		return comprovanteRepo.findAll();
	}

	public Comprovante findById(String id) {

		Optional<Comprovante> obj = comprovanteRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Comprovante.class.getName()));
	}
	
	public Comprovante novoComprovanteFromRegistro(Registro registro) {
		Comprovante comprovante ;
		if(registro.getAjustado().equals("Y")) {
			comprovante = new Comprovante();
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(registro.getRegAjustado().getInstanteRegistro()-10800000);
			
			comprovante.setNomeFuncionario(registro.getFuncionario().getNome());
			comprovante.setIdFuncionario(registro.getFuncionario().getId());
			comprovante.setAnoRegistro(registro.getRegAjustado().getAno());
			comprovante.setDiaRegistro(registro.getRegAjustado().getDia());
			comprovante.setMesRegistro(registro.getRegAjustado().getMes());
			comprovante.setInstanteRegistro(registro.getRegAjustado().getInstanteRegistro());
			comprovante.setCnpj(registro.getEmpresa().getCnpj());
			comprovante.setNomeEmpresa(registro.getEmpresa().getNome());
			comprovante.setDtHoraRegistro(calendar.getTime());
			comprovante.setTipoComprovanteRegistro(registro.getRegAjustado().getTipoRegistro());
			comprovante.setHashComprovante("");
		}else {
			comprovante = new Comprovante(registro);
		}
		
		comprovante.setPisFuncionario(registro.getFuncionario().getPis());
		comprovante.setCnpj(registro.getEmpresa().getCnpj());
		comprovante.setNomeEmpresa(registro.getEmpresa().getNome());
		comprovante.setHashComprovante(hashComprovante(comprovante));
		comprovante=comprovanteRepo.save(comprovante);
		return comprovante;
	}
	
	public String hashComprovante(Comprovante comprovante) {
		
	    String password =  comprovante.getIdFuncionario().concat(comprovante.getInstanteRegistro().toString());

	    return  DigestUtils
	      .md5Hex(password).toUpperCase();
	        
	    
	}
	
	public List<Comprovante> buscaComprovantesData(String dia,String mes,String ano,String email) {
		//Funcionario func = funcionarioService.findByEmail(email);
		
		List<Comprovante> ultimosregs =  new ArrayList<>();
		ultimosregs = comprovanteRepo.findByDiaRegistroAndMesRegistroAndAnoRegistroOrderByInstanteRegistroDesc(Integer.parseInt(dia),Integer.parseInt(mes),Integer.parseInt(ano));
		return ultimosregs;
		
	}
}
