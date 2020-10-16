package com.inovareti.rpontobackend.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inovareti.rpontobackend.services.exceptions.FileException;


@Service
public class ImageService {
	
	
	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		
		if(!"png".toUpperCase().equals(ext.toUpperCase()) && !"jpg".toUpperCase().equals(ext.toUpperCase())) {
			throw new FileException("Arquivo inválido");
		}
		
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if("png".toUpperCase().equals(ext.toUpperCase())){
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new  FileException("Erro na leitura do arquivo");
		}
		
	}

	public BufferedImage pngToJpg(BufferedImage img) {
		
		BufferedImage jpgImage = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}
	
	public InputStream getInputStream(BufferedImage img, String ext) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, ext, os);
			return new ByteArrayInputStream(os.toByteArray());
		}catch(IOException e) {
			throw new  FileException("Erro na leitura do arquivo");
			
		}
	}
	
	public BufferedImage cropSquare(BufferedImage imgSource) {
		int min = (imgSource.getHeight()<=imgSource.getWidth()) ? imgSource.getHeight() : imgSource.getWidth();
		return Scalr.crop(imgSource, (imgSource.getWidth()/2)-(min/2), (imgSource.getHeight()/2)-(min/2), min,min);
	}
	
	public BufferedImage resize(BufferedImage imgSource,int size) {
		return Scalr.resize(imgSource, Scalr.Method.ULTRA_QUALITY, size);
	}
}
