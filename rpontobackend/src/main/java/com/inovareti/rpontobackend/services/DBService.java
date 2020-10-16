package com.inovareti.rpontobackend.services;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.inovareti.rpontobackend.domain.Empresa;
import com.inovareti.rpontobackend.domain.Endereco;
import com.inovareti.rpontobackend.domain.Funcionario;
import com.inovareti.rpontobackend.domain.Registro;
import com.inovareti.rpontobackend.enums.Perfil;
import com.inovareti.rpontobackend.repositories.EmpresaRepository;
import com.inovareti.rpontobackend.repositories.EnderecoRepository;
import com.inovareti.rpontobackend.repositories.FuncionarioRepository;
import com.inovareti.rpontobackend.repositories.RegistroRepository;

@Service
public class DBService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private RegistroRepository registroRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void instantiateTestDatabase() throws ParseException {
		System.out.println("iniciando banco de  dados");
		Calendar aux = Calendar.getInstance(new Locale("pt","BR"));
		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		TimeZone.setDefault(tz);
		aux.setTimeZone(tz);
		//System.out.println("TimeZone atual é : "+aux.getTimeZone());
		//System.out.println("Date/Time atual é : "+aux.getTime());
		//System.out.println("milisegundos atual é : "+aux.getTimeInMillis());
		empresaRepository.deleteAll();
		Empresa empresa1= new Empresa(null,"Empresa Fabio","82827332");
		empresaRepository.saveAll(Arrays.asList(empresa1));
		Endereco end1 = new Endereco(null, "Av Das americas", 3500, "apto605", "Barra da Tijuca", "227234-344");
		enderecoRepository.saveAll(Arrays.asList(end1));
		funcionarioRepository.deleteAll();
		
		Funcionario func1= new Funcionario(null,"Fabio Silva de Albuquerque","fabiosalbuquerque@gmail.com","05284255798",pe.encode("123"),empresa1);
		
		func1.addPerfil(Perfil.ADMIN);
		func1.setEndereco(end1);
		
		funcionarioRepository.saveAll(Arrays.asList(func1));
		
		//registroRepository.deleteAll();
		Date agora = new Date(System.currentTimeMillis());
		Registro reg1 = new Registro(null, agora.getTime(), "Entrada", func1);
		
		registroRepository.saveAll(Arrays.asList(reg1));
		
	}
}
