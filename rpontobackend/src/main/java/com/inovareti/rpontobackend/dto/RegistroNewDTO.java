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
	private String funcionarioId;
	private String funcionarioNome;
	@NotEmpty(message="email Obrigatório")
	private String funcionarioEmail;
	private String empresaNome;
	private String empresaId;
	
	
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

	public String getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(String funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	public String getFuncionarioNome() {
		return funcionarioNome;
	}

	public void setFuncionarioNome(String funcionarioNome) {
		this.funcionarioNome = funcionarioNome;
	}

	public String getFuncionarioEmail() {
		return funcionarioEmail;
	}

	public void setFuncionarioEmail(String funcionarioEmail) {
		this.funcionarioEmail = funcionarioEmail;
	}

	public String getEmpresaNome() {
		return empresaNome;
	}

	public void setEmpresaNome(String empresaNome) {
		this.empresaNome = empresaNome;
	}

	public String getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(String empresaId) {
		this.empresaId = empresaId;
	}

	
	
	

}
