package com.inovareti.rpontobackend.services;

import java.awt.image.BufferedImage;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inovareti.rpontobackend.domain.Funcionario;
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
	private ImageService imgService;
	
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
	
}
