package com.inovareti.rpontobackend.servicies;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.inovareti.rpontobackend.domain.Empresa;
import com.inovareti.rpontobackend.domain.Funcionario;
import com.inovareti.rpontobackend.enums.Perfil;
import com.inovareti.rpontobackend.repositories.EmpresaRepository;
import com.inovareti.rpontobackend.repositories.FuncionarioRepository;

@Service
public class DBService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void instantiateTestDatabase() throws ParseException {
		System.out.println("iniciando banco de  dados");
		
		empresaRepository.deleteAll();
		Empresa empresa1= new Empresa(null,"Empresa Fabio","82827332");
		empresaRepository.saveAll(Arrays.asList(empresa1));
		
		funcionarioRepository.deleteAll();
		
		Funcionario func1= new Funcionario(null,"Fabio Silva de Albuquerque","fabiosalbuquerque@gmail.com","05284255798",pe.encode("123"),null);
		func1.setEmpresa(empresa1);
		func1.addPerfil(Perfil.ADMIN);
		
		funcionarioRepository.saveAll(Arrays.asList(func1));
		
		
	}
}
