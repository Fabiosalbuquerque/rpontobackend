package com.inovareti.rpontobackend.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.inovareti.rpontobackend.services.exceptions.FileException;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3client;
	
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);
	
	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartfile) {

		try {
			LOG.info("Coletando informações");
			String nomearquivo = multipartfile.getOriginalFilename();
			InputStream is = multipartfile.getInputStream();
			String contentType = multipartfile.getContentType();
			return uploadFile(is, nomearquivo, contentType);
		} catch (IOException e) {
			throw new FileException("erro de IO: "+ e.getMessage());
		}
		

	}

	public URI uploadFile(InputStream is, String filename, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			LOG.info("Iniciando Upload");
			s3client.putObject(bucketName, filename, is, meta);
			LOG.info("Upload finalizado");
			return s3client.getUrl(bucketName, filename).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro na conversao de url");
		}
	}
}
