package com.inovareti.rpontobackend.services;

import java.awt.image.BufferedImage;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inovareti.rpontobackend.domain.Endereco;
import com.inovareti.rpontobackend.domain.Funcionario;
import com.inovareti.rpontobackend.dto.NovoFuncionarioDTO;
import com.inovareti.rpontobackend.enums.Perfil;
import com.inovareti.rpontobackend.repositories.FuncionarioRepository;
import com.inovareti.rpontobackend.security.UserSS;
import com.inovareti.rpontobackend.services.exceptions.AuthorizationException;


@Service
public class FuncionarioService {

	
	@Autowired
	private FuncionarioRepository funcRepo;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private EmpresaService empresaServe;
	
	@Autowired
	private ImageService imgService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	
	
	@Value("${img.prefix.client.profile}")
	private String prefixo;
	
	@Value("${img.profile.size}")
	private Integer tamanho;
	
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
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		BufferedImage img = imgService.getJpgImageFromFile(multipartFile);
		String filename = prefixo+user.getid()+".jpg";
		img = imgService.cropSquare(img);
		img = imgService.resize(img, tamanho);
		return s3Service.uploadFile(imgService.getInputStream(img, "jpg"), filename, "image");
		
	}
	
	public Funcionario insert(Funcionario obj) {
		obj.setId(null);
		if(funcRepo.findByEmail(obj.getEmail())!=null) {
			throw new AuthorizationException("Email já existente");
		}else if(funcRepo.findByCpf(obj.getCpf())!=null){
			throw new AuthorizationException("CPF já existente");
		}else {
			obj = funcRepo.save(obj);
			return obj;
		}
	}
	
	public Funcionario novoFuncionarioFromDTO(NovoFuncionarioDTO objdto) {
		Funcionario novo = new Funcionario();
		novo.setNome(objdto.getNome());
		novo.setCpf(objdto.getCpfOuCnpj());
		novo.setEmail(objdto.getEmail());
		novo.setId(null);
		novo.setSenha(pe.encode(objdto.getSenha()));
		novo.addPerfil(Perfil.FUNCIONARIO);
		novo.setEmpresa(empresaServe.findById(objdto.getEmpresaId()));
		Endereco end = new Endereco();
		end.setBairro(objdto.getEnderecoBairro());
		end.setLogradouro(objdto.getEnderecoLogradouro());
		end.setComplemento(objdto.getEnderecoComplemento());
		end.setCep(objdto.getEnderecoCEP());
		end.setNumero(Integer.parseInt(objdto.getEnderecoNumero()));
		end.setId(null);
		novo.setEndereco(enderecoService.saveNewEndereco(end));
		
		return novo;
	}
}
