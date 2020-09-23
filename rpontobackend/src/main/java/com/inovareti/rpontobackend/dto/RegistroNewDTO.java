package com.inovareti.rpontobackend.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class RegistroNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Date dateRegistro;
	private Long instante;
	@NotEmpty(message="Tipo de Registro Obrigatório")
	private String tipoRegistro;
	@NotEmpty(message="email Obrigatório")
	private String email;
	
	public RegistroNewDTO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateRegistro() {
		return dateRegistro;
	}

	public void setDateRegistro(Date dateRegistro) {
		this.dateRegistro = dateRegistro;
	}

	public Long getInstante() {
		return instante;
	}

	public void setInstante(Long instante) {
		this.instante = instante;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
